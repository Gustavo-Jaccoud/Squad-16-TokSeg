package com.tokseg.storage.domain.user;

public record RegisterDTO(String email, String password, UserRole role) {
}
