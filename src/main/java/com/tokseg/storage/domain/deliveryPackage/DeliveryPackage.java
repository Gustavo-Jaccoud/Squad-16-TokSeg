package com.tokseg.storage.domain.deliveryPackage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Table(name = "delivery_package")
@Entity(name = "delivery_package")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID deliveryPersonId;
    private UUID compartmentId;
    private UUID apartmentId;
    private LocalDateTime deliveryDatetime;
    private LocalDateTime maxPickupDatetime;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private PackageStatus packageStatus;

    public DeliveryPackage(UUID deliveryPersonId, UUID compartmentId, UUID apartmentId) {
        this.deliveryPersonId = deliveryPersonId;
        this.compartmentId = compartmentId;
        this.apartmentId = apartmentId;
        this.deliveryDatetime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.maxPickupDatetime = this.deliveryDatetime.plusDays(1);
        this.packageStatus = PackageStatus.PENDING_PICKUP;
    }
}
