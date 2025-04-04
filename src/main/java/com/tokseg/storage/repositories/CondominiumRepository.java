package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.condominium.Condominium;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominiumRepository extends JpaRepository<Condominium, UUID> {
}
