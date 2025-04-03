package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.condominium.Condominium;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominiumRepository extends JpaRepository<Condominium, UUID> {
}
