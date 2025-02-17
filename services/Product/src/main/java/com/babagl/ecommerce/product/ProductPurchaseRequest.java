package com.babagl.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Le produit est  obligatoire")
        Integer productId,
        @NotNull(message = "La quantite est obligatoire")
        double quantity
) {
}
