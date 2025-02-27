package com.babagl.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Le prenom est obligatoire")
        String firstname,
        @NotNull(message = "Le prenom est obligatoire")

        String lastname,
        @NotNull(message = "Le prenom est obligatoire")
        @Email(message = "l'email du client n'est pas valide")
        String email
) {
}
