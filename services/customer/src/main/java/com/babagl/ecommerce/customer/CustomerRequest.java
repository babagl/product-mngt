package com.babagl.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(

        String id,
        @NotNull(message = "Le prenom du client est obligatoire")
        String firstname,
        @NotNull(message = "Le nom du client est obligatoire")
        String lastname,
        @NotNull(message = "Le email du client est obligatoire")
        @Email(message = "L'email n'est pas valide")
        String email,

        Address address
) {
}
