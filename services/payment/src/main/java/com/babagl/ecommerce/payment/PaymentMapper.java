package com.babagl.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    Payment toPayment(PaymentRequest request){
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId()).build();
    }
}
