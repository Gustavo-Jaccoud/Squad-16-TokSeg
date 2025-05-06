package com.tokseg.storage.services;

import com.tokseg.storage.domain.block.Block;
import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonDTO;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.domain.user.DTOs.RegisterDTO;
import com.tokseg.storage.domain.user.UserRole;
import com.tokseg.storage.repositories.DeliveryPersonRepository;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryPersonService {
    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    AuthService authService;

    @Transactional
    public ApiResponse createDeliveryPerson(DeliveryPersonDTO data){
        var user = authService.registerUser(new RegisterDTO(
                data.email(),
                data.password(),
                "DELIVERYPERSON",
                data.telephone(),
                data.name()
        ));
        if(user == null){
             return  ApiResponse.error("O entregador já possui uma conta");
        }

        DeliveryPerson newDeliveryPerson = new DeliveryPerson(user.getId(),data.cpf());
        deliveryPersonRepository.save(newDeliveryPerson);
        return ApiResponse.success(newDeliveryPerson, "Entregador criado com sucesso");


    }
}
