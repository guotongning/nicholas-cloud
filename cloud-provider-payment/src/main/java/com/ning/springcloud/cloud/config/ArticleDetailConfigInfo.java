package com.ning.springcloud.cloud.config;

import com.alibaba.fastjson.JSON;
import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import com.ning.springcloud.config.AbstractConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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

    @Override
    public String getName() {
        return "testJson";
    }

    @Override
    public ArticleDetailConfig getData() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.getData();
    }
}