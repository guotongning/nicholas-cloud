package com.ning.springcloud.api.interfaces;


import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.response.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RequestMapping("/payment")
public interface IPaymentService {

    @PostMapping(value = "/create")
    CommonResult<Payment> createPayment(Payment payment);

    @GetMapping(value = "/select/id/{id}")
    CommonResult<Payment> selectPaymentById(Long id);

    @GetMapping(value = "/select/orderNo/{orderNo}")
    CommonResult<Payment> selectPaymentByOrderNo(String orderNo);
}
