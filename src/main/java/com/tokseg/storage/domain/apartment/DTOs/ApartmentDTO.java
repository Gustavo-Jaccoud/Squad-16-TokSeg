package com.tokseg.storage.domain.apartment.DTOs;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApartmentDTO(
                @NotBlank(message = "O numero do apartamento não pode estar em branco") String apartmentNumber,

                UUID userId,

                @NotNull(message = "O id do bloco não pode estar em branco") UUID blockId) {
}
