package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, UUID> {
}
