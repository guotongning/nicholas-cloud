package com.ning.springcloud.cloud.controller;

import com.ning.springcloud.api.interfaces.INacosConfigurationService;
import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import com.ning.springcloud.cloud.config.ArticleDetailConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description
 * @date 2020/7/15 14:06
 * @created by 不在能知，乃在能行 ——nicholas
 */
@RestController
@Slf4j
@RequestMapping("/config")
public class NacosConfigurationController implements INacosConfigurationService {

    @Resource
    private ArticleDetailConfigInfo articleDetailConfigInfo;

    @GetMapping("/testJson")
    public ArticleDetailConfig getTestConfig() {
        return articleDetailConfigInfo.getData();
    }

}
