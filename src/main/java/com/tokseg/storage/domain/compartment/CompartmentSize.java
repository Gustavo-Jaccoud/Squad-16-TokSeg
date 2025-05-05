package com.tokseg.storage.domain.compartment;

public enum CompartmentSize {

    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String size;

    CompartmentSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}

