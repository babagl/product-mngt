package com.babagl.ecommerce.order;

import com.babagl.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Le montant doit etre positif")
        BigDecimal amount,
        @NotNull(message = "Le methode de payement doit etre renseigne")
        PaymentMethod paymentMethod,
        @NotNull(message = "Le client doit etre present si vous n'etes pas connecte veuillez vous connecter")
        @NotBlank(message = "Le client doit etre present si vous n'etes pas connecte veuillez vous connecter")
        @NotEmpty(message = "Le client doit etre present si vous n'etes pas connecte veuillez vous connecter")
        String customerId,
        @NotEmpty(message = "vous devriez au moins acheter un produit")
        List<PurchaseRequest> products
) {
}
