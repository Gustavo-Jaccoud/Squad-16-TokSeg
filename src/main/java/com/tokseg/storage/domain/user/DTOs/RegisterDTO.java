package com.tokseg.storage.domain.user.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public record RegisterDTO(
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,

        @Pattern(regexp = "ADMIN|RESIDENT|DELIVERYPERSON", message = "O papel do usuário deve ser ADMIN, RESIDENT ou DELIVERYPERSON")
        @NotBlank(message = "O papel do usuário é obrigatório")
        String role,

        @NotBlank(message = "O telefone não pode estar em branco")
        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 dígitos")
        String telephone,

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 4, message = "O nome precisa de pelo menos  4 digitos")
        String name

) {
}
