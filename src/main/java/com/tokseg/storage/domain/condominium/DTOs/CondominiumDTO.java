package com.tokseg.storage.domain.condominium.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CondominiumDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 4, message = "O nome precisa de pelo menos  4 digitos")
        String name,

        @NotBlank(message = "O endereço não pode estar em branco")
        @Size(min = 6, message = "O endereço precisa de pelo menos  6 digitos")
        String address,


        @NotBlank(message = "O telefone não pode estar em branco")
        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 dígitos")
        String telephone) {
}
