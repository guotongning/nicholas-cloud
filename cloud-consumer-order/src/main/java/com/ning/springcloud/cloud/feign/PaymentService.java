package com.ning.springcloud.cloud.feign;

import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.api.interfaces.IPaymentService;
import com.ning.springcloud.response.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "cloud-payment-service")
public interface PaymentService extends IPaymentService {

    @GetMapping(value = "/payment/select/id/{id}")
    CommonResult<Payment> selectPaymentById(Long id);
}
