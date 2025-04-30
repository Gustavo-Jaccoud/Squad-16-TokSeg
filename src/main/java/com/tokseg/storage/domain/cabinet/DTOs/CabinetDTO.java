package com.tokseg.storage.domain.cabinet.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CabinetDTO(
        @NotNull(message = "O id do condominio não pode estar em branco")
        UUID condominiumId,

        @NotNull(message = "A localização não pode estar em branco")
        String location,

        @NotNull(message = "O status do armario não pode estar em branco")
        boolean status


) {
}
