package com.tokseg.storage.domain.deliveryPerson.DTOs;


import com.tokseg.storage.domain.user.UserRole;

import java.util.UUID;

public record DeliveryPersonResponseDTO(

        UUID id,
        String cpf,
        UserRole role,
        String name,
        String email,
        String telephone
) {
}
