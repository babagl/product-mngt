package com.babagl.ecommerce.kafka;

import com.babagl.ecommerce.customer.CustomerResponse;
import com.babagl.ecommerce.order.PaymentMethod;
import com.babagl.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> product
) {
}
