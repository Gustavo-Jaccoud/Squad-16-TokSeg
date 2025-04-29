package com.tokseg.storage.repositories;

import java.util.List;
import java.util.UUID;

import com.tokseg.storage.domain.block.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokseg.storage.domain.apartment.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {
    List<Apartment> findByBlockId(UUID blockId);
    List<Apartment> findByUserId(UUID userId);

}
