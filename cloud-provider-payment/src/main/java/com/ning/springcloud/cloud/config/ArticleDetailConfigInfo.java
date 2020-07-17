package com.ning.springcloud.cloud.config;

import com.alibaba.fastjson.JSON;
import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArticleDetailConfigInfo extends AbstractConfigInfo<ArticleDetailConfig> {
    @Override
    public String getDataId() {
        return "cloud-provider-payment:testJson";
    }

    @Override
    public void compile(String dataStr) {
        data = JSON.parseObject(dataStr, ArticleDetailConfig.class);
    }

}