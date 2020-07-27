package com.ning.springcloud.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description
 * @date 2020/7/27 14:13
 * @created by 不在能知，乃在能行 ——nicholas
 */
@Slf4j
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().toString();
        InetSocketAddress remoteAddress = exchange.getRequest().getRemoteAddress();
        //把ip地址塞到request header里，不然待会web框架获取不到ip
        String clientIp = Objects.requireNonNull(remoteAddress).getAddress().getHostAddress();
        ServerHttpRequest mutatedServerHttpRequest = request.mutate().header("X-CLIENT-IP", clientIp).build();
        ServerWebExchange mutatedServerWebExchange = exchange.mutate().request(mutatedServerHttpRequest).build();
        //不是GET，判断是不是登录
        if (uri.contains("login")
                || uri.contains("logout")
        ) {
            return chain.filter(mutatedServerWebExchange);
        }
        //获取cookie中的token令牌
        MultiValueMap<String, HttpCookie> multiValueMap = exchange.getRequest().getCookies();
        List<HttpCookie> cookies = multiValueMap.get("token");
        if (cookies != null && cookies.size() != 0) {
            String token = cookies.get(0).getValue();
            //token为空说明未登录，不放行
            if (StringUtils.isBlank(token)) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
            //token not null
            try {
                Map<String, Object> verifyToken = JwtUtil.verifyToken(token);
                String userId = (String) verifyToken.get("userId");
                String userLoginIP = redisTemplate.opsForValue().get("token_" + userId);
                //判断是否被token盗用
                if (!StringUtils.equals(String.valueOf(userLoginIP), clientIp)) {
                    //TODO 警告用户上次登录地点与现在不一致,进行安全验证。
                }
                return chain.filter(mutatedServerWebExchange);
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }
        }
        //没有cookie不放行
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
}
