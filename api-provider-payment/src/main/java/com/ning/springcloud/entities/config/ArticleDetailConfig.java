package com.ning.springcloud.entities.config;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2020/7/9 15:38
 * @Created by nicholas
 */
@Data
public class ArticleDetailConfig {
    /**
     * 文章详情页分享优化-- 红色长条按钮文案
     */
    private String contextLongButton;
    /**
     * 文章详情页分享优化-- 底部吸底栏评论框位置按钮文案
     */
    private String contextBottom;
    /**
     * 文章详情页分享优化-- 功能开关。
     */
    private Boolean shareOptimizedSwitch;

    /**
     * 长条按钮下方文案。aa$xx$bb xx是前端改变显示格式的文本
     */
    private String contextUnderLongButton;

    /**
     * 右上角icon配置
     */
    private List<IconConfig> topIconList;
}
