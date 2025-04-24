package com.tokseg.storage.domain.user.DTOs;

import com.tokseg.storage.domain.user.UserRole;

import java.util.UUID;

public record UserDTO(UUID id, String name, String email, UserRole role, String telephone) {
}
