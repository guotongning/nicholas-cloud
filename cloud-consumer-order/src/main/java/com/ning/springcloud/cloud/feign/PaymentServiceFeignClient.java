package com.ning.springcloud.cloud.feign;

import com.ning.springcloud.api.interfaces.IPaymentService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CloudPaymentService", contextId = "payment-service")
public interface PaymentServiceFeignClient extends IPaymentService {

}
