package com.tokseg.storage.domain.deliveryPackage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.domain.notification.Notification;
import com.tokseg.storage.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Table(name = "delivery_package")
@Entity(name = "delivery_package")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"deliveryPerson", "compartment", "apartment", "pickedUpBy"})
public class DeliveryPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_person_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private DeliveryPerson deliveryPerson;

    @JsonProperty("deliveryPersonId")
    public UUID getDeliveryPersonId() {
        return deliveryPerson != null ? deliveryPerson.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compartment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Compartment compartment;

    @JsonProperty("compartmentId")
    public UUID getCompartmentId() {
        return compartment != null ? compartment.getId() : null;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Apartment apartment;

    @JsonProperty("apartmentId")
    public UUID getApartmentId() {
        return apartment != null ? apartment.getId() : null;
    }

    @Column(nullable = false)
    private LocalDateTime deliveryDatetime;
    @Column(nullable = false)
    private LocalDateTime maxPickupDatetime;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private PackageStatus packageStatus;

    @Column(nullable = true)
    private LocalDateTime pickupDatetime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickedUpBy", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User pickedUpBy;

    @JsonProperty("pickedUpBy")
    public UUID getUserId() {
        return pickedUpBy != null ? pickedUpBy.getId() : null;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    public DeliveryPackage(DeliveryPerson deliveryPerson, Compartment compartment, Apartment apartment) {
        this.deliveryPerson = deliveryPerson;
        this.compartment = compartment;
        this.apartment = apartment;
        this.deliveryDatetime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.maxPickupDatetime = this.deliveryDatetime.plusDays(1);
        this.packageStatus = PackageStatus.PENDING_PICKUP;
    }
}
