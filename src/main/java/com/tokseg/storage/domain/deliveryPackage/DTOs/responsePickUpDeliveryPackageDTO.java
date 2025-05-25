package com.tokseg.storage.domain.deliveryPackage.DTOs;

import java.util.UUID;

public record responsePickUpDeliveryPackageDTO(
                boolean unlock,
                UUID compartmentId) {

}