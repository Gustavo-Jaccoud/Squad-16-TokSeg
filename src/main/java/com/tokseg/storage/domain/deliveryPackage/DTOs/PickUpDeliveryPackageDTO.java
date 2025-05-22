package com.tokseg.storage.domain.deliveryPackage.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PickUpDeliveryPackageDTO(
        @NotNull(message = "O id do compartimento não pode estar em branco")
        UUID compartmentId,
        @NotNull(message = "O usuario não pode estar em branco")
        String username,
        @NotNull(message = "A senha não pode estar em branco")
        String password
) {
}
