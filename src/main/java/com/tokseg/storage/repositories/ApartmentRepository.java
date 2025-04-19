package com.tokseg.storage.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokseg.storage.domain.apartment.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

}
