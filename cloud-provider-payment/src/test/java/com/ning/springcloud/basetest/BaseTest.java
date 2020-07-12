package com.ning.springcloud.basetest;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: nicholas
 * @Date: 2020/7/12 17:12
 * @Descreption:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BaseTest {

    protected String className = this.getClass().getSimpleName();

    @Before
    public void before() {
        log.info("==========test " + className + " start==========");
    }

    @After
    public void after() {
        log.info("===========test " + className + " end===========");
    }
}
