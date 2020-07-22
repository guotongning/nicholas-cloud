package com.ning.springcloud.api.interfaces;


import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/config")
public interface INacosConfigurationService {

    @GetMapping("/testJson")
    ArticleDetailConfig getTestJson();
}
