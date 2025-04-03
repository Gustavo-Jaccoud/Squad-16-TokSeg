package com.tokseg.storage.domain.condominium.DTOs;

import jakarta.validation.constraints.NotBlank;

public record CreateCondominiumDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String name,

        @NotBlank(message = "O endereço não pode estar em branco")
        String address,

        @NotBlank(message = "O telefone não pode estar em branco")
        String telephone) {
}
