package com.tokseg.storage.domain.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID deliveryPackageId;
    private LocalDateTime sentDatetime;
    @Column(name = "status")
    private NotificationStatus notificationStatus;

    public Notification(UUID deliveryPackageId, NotificationStatus notificationStatus) {
        this.deliveryPackageId = deliveryPackageId;
        this.notificationStatus = notificationStatus;
    }
}
