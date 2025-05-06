package com.tokseg.storage.domain.deliveryPerson;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "delivery_person")
@Entity(name = "delivery_person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;

    private String cpf;

    public DeliveryPerson(UUID userId, String cpf) {
        this.userId = userId;
        this.cpf = cpf;
    }
}
