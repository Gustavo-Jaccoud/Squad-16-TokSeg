package com.tokseg.storage.domain.deliveryPerson.DTOs;


import java.util.UUID;

public record DeliveryPersonResponseDTO(

        UUID id,
        String cpf,
        String role,
        String name,
        String email,
        String telephone
) {
}
