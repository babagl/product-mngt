package com.babagl.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
     @NotNull(message = "Produit est obligatoire")
     Integer productId,

     @Positive(message = "la quantites est obligatoire")
     double quantity
) {
}
