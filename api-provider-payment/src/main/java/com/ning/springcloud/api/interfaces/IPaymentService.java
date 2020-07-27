package com.ning.springcloud.api.interfaces;


import com.ning.springcloud.api.dto.Payment;
import com.ning.springcloud.response.CommonResult;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/payment")
public interface IPaymentService {

    @PostMapping(value = "/create")
    CommonResult<Payment> createPayment(@RequestBody Payment payment);

    @GetMapping(value = "/select/id/{id}")
    CommonResult<Payment> selectPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/select/orderNo/{orderNo}")
    CommonResult<Payment> selectPaymentByOrderNo(@PathVariable("orderNo") String orderNo);

    @GetMapping("/image/wechat")
    String getPaymentImage();

}
