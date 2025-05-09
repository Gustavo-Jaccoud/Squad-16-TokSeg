package com.tokseg.storage.domain.notification;

public enum NotificationStatus {
    FAILED("failed"),
    SENT("sent");

    private String status;
    NotificationStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
