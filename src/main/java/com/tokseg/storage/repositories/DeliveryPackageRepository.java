package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryPackageRepository extends JpaRepository<DeliveryPackage, UUID> {
}
