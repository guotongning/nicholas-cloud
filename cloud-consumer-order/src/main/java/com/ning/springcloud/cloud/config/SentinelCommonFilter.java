package com.ning.springcloud.cloud.config;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.adapter.servlet.util.FilterUtil;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SentinelCommonFilter implements Filter {
    public final static String HTTP_METHOD_SPECIFY = "HTTP_METHOD_SPECIFY";
    public final static String EXCLUDE_URLS = "EXCLUDE_URLS";
    private final static String COLON = ":";
    private final static String ROOT_PATH = "/";
    private boolean httpMethodSpecify = false;
    private List<String> excludeUrls = new ArrayList<>();

    private Map<HandlerMethod, String> handlerMethodUrlMap = new ConcurrentHashMap<>(32);

    @Resource
    private DispatcherServlet dispatcherServlet;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String originalTarget = FilterUtil.filterTarget(httpServletRequest);
        if (excludeUrls.stream().anyMatch(url -> antPathMatcher.match(url, originalTarget))) {
            chain.doFilter(request, response);
            return;
        }
        Entry entry = null;
        Entry methodEntry = null;
        try {
            String target = this.resolveTarget(httpServletRequest);
            // Parse the request origin using registered origin parser.
            String origin = parseOrigin(httpServletRequest);

            ContextUtil.enter(target, origin);
            entry = SphU.entry(target, EntryType.IN);
            // Add method specification if necessary
            if (httpMethodSpecify) {
                methodEntry = SphU.entry(httpServletRequest.getMethod().toUpperCase() + COLON + target, EntryType.IN);
            }
            chain.doFilter(request, response);
        } catch (BlockException e) {
            // Return the block page, or redirect to another URL.
            WebCallbackManager.getUrlBlockHandler().blocked(httpServletRequest, httpServletResponse, e);
        } catch (Exception e2) {
            Tracer.trace(e2);
            throw e2;
        } finally {
            if (methodEntry != null) {
                methodEntry.exit();
            }
            if (entry != null) {
                entry.exit();
            }
            ContextUtil.exit();
        }
    }

    /**
     * Use Spring Mvc principle that searching the best matching HandlerMethod(aka. Controller Method)
     * 使用SpringMVC的查询Handler机制，查找合适的`HandlerMethod`
     *
     * @param request servlet http request
     * @return
     */
    protected String resolveTarget(HttpServletRequest request) {
        String pattern = "";
        for (HandlerMapping mapping : dispatcherServlet.getHandlerMappings()) {
            HandlerExecutionChain handler = null;
            try {
                handler = mapping.getHandler(request);
                // handler hit, then resolve resource name from Controller and it's Controller method
                if (handler != null) {
                    Object handlerObject = handler.getHandler();
                    if (handlerObject instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handlerObject;
                        //use it as cache
                        pattern = handlerMethodUrlMap.getOrDefault(handlerMethod, "");
                        if (StringUtils.isEmpty(pattern)) {
                            //提取Controller方法上的注解值，拼装成Pattern
                            pattern = resolveResourceNameHandlerMethod(handlerMethod);
                            handlerMethodUrlMap.put(handlerMethod, pattern);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Clean and unify the URL.
        // For REST APIs, you have to clean the URL (e.g. `/foo/1` and `/foo/2` -> `/foo/:id`), or
        // the amount of context and resources will exceed the threshold.
        return pattern;

    }

    /**
     * A HandlerMethod object usually represents  a controller's method which is annotated with
     *
     * @param handlerMethod An object that represents an Controller's Method
     * @return the resource name
     * @RequestMapping ,@GetMapping , @PostMapping, @DeleteMapping and so on,
     * so the resource can be represented with the controller's methods correspondingly.
     * Although Controller's methods is not good option to represent Resources.
     * As a result , the Annotations on Controllers and their methods can be introspected according to
     * Spring original mechanisms.
     * <p>
     * The Resource should use following patterns:
     * <Http_method>:<Controller-class-level-url-annotation><Controller-method-level-url-annotation>
     */
    private String resolveResourceNameHandlerMethod(HandlerMethod handlerMethod) {
        String target;
        String typeMapping = "";
        RequestMapping typeRequestMapping =
                AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(), RequestMapping.class);
        if (typeRequestMapping != null && typeRequestMapping.value().length > 0) {
            typeMapping = typeRequestMapping.value()[0];
        }
        RequestMapping methodRequestMapping =
                AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getMethod(), RequestMapping.class);
        String methodMapping = methodRequestMapping.value()[0];
        if (typeMapping.length() > 1 && typeMapping.endsWith(ROOT_PATH)) {
            typeMapping = typeMapping.substring(0, typeMapping.length() - 1);
        }
        target = typeMapping + methodMapping;
        return target;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        httpMethodSpecify = Boolean.parseBoolean(filterConfig.getInitParameter(HTTP_METHOD_SPECIFY));
        String excludeUrlsString = filterConfig.getInitParameter(EXCLUDE_URLS);
        if (!StringUtils.isEmpty(excludeUrlsString)) {
            excludeUrls = Arrays.asList(excludeUrlsString.split(","));
        }

    }

    private String parseOrigin(HttpServletRequest request) {
        RequestOriginParser originParser = WebCallbackManager.getRequestOriginParser();
        String origin = EMPTY_ORIGIN;
        if (originParser != null) {
            origin = originParser.parseOrigin(request);
            if (StringUtil.isEmpty(origin)) {
                return EMPTY_ORIGIN;
            }
        }
        return origin;
    }

    @Override
    public void destroy() {

    }

    private static final String EMPTY_ORIGIN = "";
}