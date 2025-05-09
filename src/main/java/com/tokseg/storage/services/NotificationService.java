package com.tokseg.storage.services;

import com.tokseg.storage.repositories.NotificationRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public ApiResponse getAllNotification(){
        return ApiResponse.success(notificationRepository.findAll(), "Todos as notificações");
    }
}
