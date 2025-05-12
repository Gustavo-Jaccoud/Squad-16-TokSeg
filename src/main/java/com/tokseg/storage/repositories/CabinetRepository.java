package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.cabinet.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CabinetRepository extends JpaRepository<Cabinet, UUID> {
    List<Cabinet> findByCondominium_Id(UUID condominiumId);
}
