package com.tokseg.storage.services;

import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.notification.DTOs.NotificationDTO;
import com.tokseg.storage.domain.notification.Notification;
import com.tokseg.storage.repositories.NotificationRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

   public void createNotification(NotificationDTO notification){

       Notification newNotification = new Notification(
               notification.deliveryPackage(),
               notification.notificationStatus(),
               notification.notificationType()
       );

       notificationRepository.save(newNotification);
   }

    public ApiResponse getAllNotification(){
        return ApiResponse.success(notificationRepository.findAll(), "Todos as notificações");
    }
}
