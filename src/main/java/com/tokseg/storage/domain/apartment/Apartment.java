package com.tokseg.storage.domain.apartment;

import java.util.UUID;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "apartment")
@Entity(name = "apartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "block_id")
    private UUID blockId;
    @Column(name = "user_id")
    private UUID userId;
    private String apartmentNumber;

    public Apartment(UUID blockId, UUID userId, String apartmentNumber) {
        this.blockId = blockId;
        this.userId = userId;
        this.apartmentNumber = apartmentNumber;
    }

}
