package com.tokseg.storage.controllers.notification;

import com.tokseg.storage.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping()
    public ResponseEntity getAllNotification(){
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getAllNotification());
    }
}
