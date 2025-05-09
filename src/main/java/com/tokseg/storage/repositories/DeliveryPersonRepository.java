package com.tokseg.storage.repositories;

import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, UUID> {
    @Query(value = """
    SELECT
        dp.id,
        dp.cpf,
        u.email,
        u.name,
        u.telephone,
        u.role
    FROM delivery_person dp
    JOIN users u ON dp.user_id = u.id
""", nativeQuery = true)
    List<Object[]> findAllWithUserData();

}
