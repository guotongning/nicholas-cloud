package com.ning.springcloud.cloud.feign;

import com.ning.springcloud.api.interfaces.IPaymentService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cloud-payment-service", contextId = "payment-service")
public interface PaymentServiceFeignClient extends IPaymentService {

}
