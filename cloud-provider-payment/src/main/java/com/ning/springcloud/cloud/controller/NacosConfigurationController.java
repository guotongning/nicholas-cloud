package com.ning.springcloud.cloud.controller;

import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import com.ning.springcloud.api.interfaces.INacosConfigurationService;
import com.ning.springcloud.cloud.config.ArticleDetailConfigInfo;
import com.ning.springcloud.config.AbstractConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 * @date 2020/7/15 14:06
 * @created by 不在能知，乃在能行 ——nicholas
 */
@RestController
@Slf4j
public class NacosConfigurationController implements INacosConfigurationService {
    @Resource
    private ArticleDetailConfigInfo articleDetailConfigInfo;


    @Override
    public ArticleDetailConfig getTestJson() {
        return articleDetailConfigInfo.getData();
    }
}
