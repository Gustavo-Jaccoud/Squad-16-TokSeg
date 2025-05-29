package com.tokseg.storage.domain.notification;

public enum NotificationType {
        PENDING_PICKUP("pending_pickup"),
        PICKED_UP("picked_up"),
        RETRIEVED_BY_STAFF("retrieved_by_staff");


        private String type;
        NotificationType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }
    }

