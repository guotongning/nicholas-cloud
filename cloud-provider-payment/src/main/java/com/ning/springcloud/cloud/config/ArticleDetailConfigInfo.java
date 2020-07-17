package com.ning.springcloud.cloud.config;

import com.alibaba.fastjson.JSON;
import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArticleDetailConfigInfo extends AbstractConfigInfo<ArticleDetailConfig> {
    @Override
    protected String getDataId() {
        return "cloud-provider-payment:testJson";
    }

    @Override
    protected void compile(String dataStr) {
        data = JSON.parseObject(dataStr, ArticleDetailConfig.class);
    }

}