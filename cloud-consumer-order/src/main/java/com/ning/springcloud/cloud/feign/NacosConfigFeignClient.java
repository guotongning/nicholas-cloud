package com.ning.springcloud.cloud.feign;

import com.ning.springcloud.api.interfaces.INacosConfigurationService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description
 * @date 2020/7/22 11:16
 * @created by 不在能知，乃在能行 ——nicholas
 */
@FeignClient(name = "CloudPaymentService",contextId = "payment-config")
public interface NacosConfigFeignClient extends INacosConfigurationService {
}
