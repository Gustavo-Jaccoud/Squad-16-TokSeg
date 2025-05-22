package com.tokseg.storage.domain.notification.DTOs;

import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.notification.NotificationStatus;
import com.tokseg.storage.domain.notification.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationDTO(
        DeliveryPackage deliveryPackage,
        NotificationStatus notificationStatus,
        NotificationType notificationType
) {
}
