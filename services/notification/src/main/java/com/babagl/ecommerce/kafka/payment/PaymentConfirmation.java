package com.babagl.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethode paymentMethode,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
