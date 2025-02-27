package com.babagl.ecommerce.payment;

import com.babagl.ecommerce.customer.CustomerResponse;
import com.babagl.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
