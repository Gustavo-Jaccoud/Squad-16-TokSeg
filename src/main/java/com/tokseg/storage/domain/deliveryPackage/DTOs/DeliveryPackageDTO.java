package com.tokseg.storage.domain.deliveryPackage.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeliveryPackageDTO(
        @NotNull(message = "O id do entregador não pode estar em branco")
        UUID deliveryPersonId,
        @NotNull(message = "O id do compartimento não pode estar em branco")
        UUID compartmentId,
        @NotNull(message = "O id do apartamento não pode estar em branco")
        UUID apartmentId
) {
}
