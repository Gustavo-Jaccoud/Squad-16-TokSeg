package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, UUID> {
}
