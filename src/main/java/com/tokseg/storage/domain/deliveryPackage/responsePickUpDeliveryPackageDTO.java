package com.tokseg.storage.domain.deliveryPackage;

import java.util.UUID;

public record responsePickUpDeliveryPackageDTO(
        boolean unlock,
        UUID compartmentId) {

}