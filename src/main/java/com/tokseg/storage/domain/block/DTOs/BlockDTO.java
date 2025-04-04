package com.tokseg.storage.domain.block.DTOs;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;


public record BlockDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String name,
        @NotBlank(message = "O id não pode estar em branco")
        UUID condominium_id
) {
}
