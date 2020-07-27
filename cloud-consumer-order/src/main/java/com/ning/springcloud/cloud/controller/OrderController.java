package com.ning.springcloud.cloud.controller;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.api.entities.config.ArticleDetailConfig;
import com.ning.springcloud.baseutils.cache.EnableCache;
import com.ning.springcloud.cloud.feign.NacosConfigFeignClient;
import com.ning.springcloud.cloud.feign.PaymentServiceFeignClient;
import com.ning.springcloud.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

/**
 * @Author: nicholas
 * @Date: 2020/7/14 19:38
 * @Descreption:
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Resource
    private PaymentServiceFeignClient paymentService;

    @Resource
    private NacosConfigFeignClient nacosConfigFeignClient;

    @GetMapping("/get/id/{id}")
    public CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id) {
        return paymentService.selectPaymentById(id);
    }

    @GetMapping("/config/get/testJson")
    @EnableCache
    public CommonResult<ArticleDetailConfig> getConfigByName() {
        ArticleDetailConfig byName = nacosConfigFeignClient.getTestJson();
        return new CommonResult<>(200, "success", byName);
    }

    @GetMapping("/payImage/wechat")
    public void payImage(HttpServletResponse response) {
        String path = paymentService.getPaymentImage();
        try {
            response.setContentType("image/jpeg");
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            BufferedImage buffImg = ImageIO.read(new FileInputStream(path));
            ImageIO.write(buffImg, "png", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
