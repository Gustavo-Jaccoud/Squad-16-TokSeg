package com.tokseg.storage.domain.compartment.DTOs;

import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.compartment.CompartmentSize;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AvailableCompartmentRequestDTO(
        @NotNull(message = "O id do condominio não pode estar em branco")
        UUID condominiumId,
        @NotNull(message = "O tamanho não pode estar em branco")
        CompartmentSize size
) {
}
