package com.tokseg.storage.domain.compartment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "compartment")
@Entity(name = "compartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({"cabinet"})
public class Compartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cabinet_id", nullable = false)
    private Cabinet cabinet;

    @JsonProperty("cabinetId")
    public UUID getCabinetId() {
        return cabinet != null ? cabinet.getId() : null;
    }

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CompartmentSize size;
    @JsonProperty("isOccupied")

    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    @JsonIgnore
    @OneToMany(mappedBy = "compartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryPackage> deliveryPackages;

    public Compartment(Cabinet cabinet, String name, CompartmentSize size, boolean isOccupied) {
        this.cabinet = cabinet;
        this.name = name;
        this.size = size;
        this.isOccupied = isOccupied;
    }
}
