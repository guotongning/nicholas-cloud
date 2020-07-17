package com.ning.springcloud.api.entities.config;

import lombok.Data;

/**
 * 文章详情页优化，右上角ICON配置
 */
@Data
public class IconConfig {
    private Boolean display;
    private String iconUrl;
    private String jumpUrl;
}