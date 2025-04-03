package com.tokseg.storage.domain.user.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;

public record RecoverPasswordDTO(
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "Email inválido")
        String email) {
}
