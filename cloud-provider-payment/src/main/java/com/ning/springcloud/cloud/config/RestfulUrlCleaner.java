package com.ning.springcloud.cloud.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class RestfulUrlCleaner implements UrlCleaner {

    public static final String ROOT_PATH = "/";

    private Set<String> skipSet = new HashSet<>();

    public RestfulUrlCleaner() {
    }

    public RestfulUrlCleaner(Set<String> skipSet) {
        this.skipSet = skipSet;
    }

    /***
     * <p>Process the url. Some path variables should be handled and unified.</p>
     * <p>e.g. collect_item_relation--10200012121-.html will be converted to collect_item_relation.html</p>
     *
     * @param originUrl original url
     * @return processed url
     */
    @Override
    public String clean(String originUrl) {
        return originUrl;
    }

    /***
     *  根据restful接口类型，进行清空
     *  url的格式： /api/{tenantId}/name/{transactionId}
     *  提取变量信息，然后确定哪些应该被替换，当前采用的默认策略是租户编号被替换，而营销订单ID不被替换
     * @param originUrl original url
     * @param pattern 匹配的pattern
     * @return processed url
     */
    public String clean(String originUrl, String pattern) {
        if (originUrl.startsWith(ROOT_PATH)) {
            originUrl = originUrl.substring(1);
        }
        if (pattern.startsWith(ROOT_PATH)) {
            pattern = pattern.substring(1);
        }
        String[] original = originUrl.split(ROOT_PATH);
        String[] patternArray = pattern.split(ROOT_PATH);
        if (original.length != patternArray.length) {
            return originUrl;
        }
        Matcher matcher;
        StringBuilder replacedUrl = new StringBuilder();
        for (int i = 0; i < patternArray.length; i++) {
            replacedUrl.append(ROOT_PATH);
            if (patternArray[i].startsWith("{") && patternArray[i].endsWith("}")) {
                replacedUrl.append("*");
            } else {
                replacedUrl.append(""+patternArray[i]);
            }
        }
        return replacedUrl.toString();
    }
}