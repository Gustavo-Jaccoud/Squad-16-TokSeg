package com.tokseg.storage.domain.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "notification")
@Entity(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_package_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DeliveryPackage deliveryPackage;

    @JsonProperty("deliveryPackageId")
    public UUID getBlockId() {
        return deliveryPackage != null ? deliveryPackage.getId() : null;
    }

    @Column(nullable = false)
    private LocalDateTime sentDatetime;
    @Column(name = "status",nullable = false)
    private NotificationStatus notificationStatus;

    public Notification(DeliveryPackage deliveryPackage, NotificationStatus notificationStatus) {
        this.deliveryPackage = deliveryPackage;
        this.notificationStatus = notificationStatus;
    }
}
