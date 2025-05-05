package com.tokseg.storage.domain.compartment.DTOs;

import com.tokseg.storage.domain.compartment.CompartmentSize;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompartmentDTO(
        @NotNull(message = "O id do armario não pode estar em branco")
        UUID cabinetId,

        @NotNull(message = "O nome não pode estar em branco")
        String name,

        @NotNull(message = "O tamanho não pode estar em branco")
        CompartmentSize size,


        @NotNull(message = "A ocupação do compartimento não pode estar em branco")
        boolean isOccupied


) {
}