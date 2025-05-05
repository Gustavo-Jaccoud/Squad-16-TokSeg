package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.compartment.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompartmentRepository extends JpaRepository<Compartment, UUID> {
    List<Compartment> findByCabinetId(UUID CabinetId);
}
