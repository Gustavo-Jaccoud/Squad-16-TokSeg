package com.tokseg.storage.domain.deliveryPackage;

public enum PackageStatus {
    PENDING_PICKUP("pending_pickup"),
    PICKED_UP("picked_up"),
    RETRIEVED_BY_STAFF("retrieved_by_staff");

    private String status;
    PackageStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
