package com.babagl.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
         Integer id,
         @NotNull(message = "le Nom du produit est obligatoire")
         String name,
         @NotNull(message = "La Description est oligatoire")
         String description,
         @Positive(message = "La quantite disponible doit etre positive")
         double availableQuantity,

         @Positive(message = "Price doit etre positive")
         BigDecimal price,
         @NotNull(message = "La category du produit est obligatoire")
         Integer categoryId
) {
}
