package com.tokseg.storage.domain.block.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;


public record BlockDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String name,
        @NotBlank(message = "O id não pode estar em branco")
        @UUID(message = "O id não é valido")
        UUID condominium_id
) {
}
