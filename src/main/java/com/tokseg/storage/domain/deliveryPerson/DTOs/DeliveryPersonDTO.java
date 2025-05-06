package com.tokseg.storage.domain.deliveryPerson.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record DeliveryPersonDTO(
        @NotBlank(message = "O cpf não pode estar em branco")
        @CPF
        String cpf,
        @NotNull(message = "O id não pode estar em branco")
        String name,
        @NotNull(message = "O email não pode estar em branco")
        @Email(message = "O email é invalido")
        String email,
        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,
        @NotNull(message = "O telefone não pode estar em branco")
        String telephone
) {
}
