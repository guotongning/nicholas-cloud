package com.ning.springcloud.service;

import com.ning.springcloud.basetest.BaseTest;
import com.ning.springcloud.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @date 2020/7/14 10:47
 * @created by 不在能知，乃在能行 ——nicholas
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest extends BaseTest {

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void test() {
        String hKey = "person";
        String key = "nicholas";
        String value = "nicholas is handsome";
        boolean set = redisUtils.set(key, value);
        log.info("set = {}", set);
        String valueRedis = (String) redisUtils.get(key);
        log.info("value = {}", valueRedis);
        boolean expireSuccess = redisUtils.expire(key, 60, TimeUnit.SECONDS);
        log.info("设置剩余时间 {}", expireSuccess);
        long expire = redisUtils.getExpire(key);
        log.info("key 剩余时间={}", expire);
        redisUtils.del(key);
        long expireAfterDel = redisUtils.getExpire(key);
        log.info("删除后 expire = {}", expireAfterDel);
    }
}
