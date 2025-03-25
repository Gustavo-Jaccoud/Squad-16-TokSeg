package com.tokseg.storage.domain.user;

public enum UserRole {

    ADMIN("admin"),
    RESIDENT("resident");

    private String role;
    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
