package com.ning.springcloud.controller;

import com.ning.springcloud.common.response.CommonResult;
import com.ning.springcloud.config.ArticleDetailConfigInfo;
import com.ning.springcloud.entities.config.ArticleDetailConfig;
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
public class NacosConfigurationController {

    @Resource
    private ArticleDetailConfigInfo articleDetailConfigInfo;

    @GetMapping("/testJson")
    public CommonResult<ArticleDetailConfig> getTestConfig() {
        ArticleDetailConfig data = articleDetailConfigInfo.getData();
        return new CommonResult<>(200, "success", data);
    }

}
