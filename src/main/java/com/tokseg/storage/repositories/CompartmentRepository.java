package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.compartment.CompartmentSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompartmentRepository extends JpaRepository<Compartment, UUID> {
    List<Compartment> findByCabinet_Id(UUID CabinetId);
    Compartment findFirstByCabinet_IdAndIsOccupiedAndSize(UUID cabinetId, boolean isOccupied, CompartmentSize size);
}
