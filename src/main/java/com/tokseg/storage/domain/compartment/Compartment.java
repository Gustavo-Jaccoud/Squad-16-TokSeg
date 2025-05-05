package com.tokseg.storage.domain.compartment;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "compartment")
@Entity(name = "compartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "cabinet_id")
    private UUID cabinetId;
    private String name;
    @Enumerated(EnumType.STRING)
    private CompartmentSize size;
    @JsonProperty("isOccupied")
    @Column(name = "is_occupied")
    private boolean isOccupied;

    public Compartment(UUID cabinetId, String name, CompartmentSize size, boolean isOccupied) {
        this.cabinetId = cabinetId;
        this.name = name;
        this.size = size;
        this.isOccupied = isOccupied;
    }
}
