package com.tokseg.storage.services;


import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonDTO;
import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonResponseDTO;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.domain.user.DTOs.RegisterDTO;
import com.tokseg.storage.repositories.DeliveryPersonRepository;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeliveryPersonService {
    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;


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

        DeliveryPerson newDeliveryPerson = new DeliveryPerson(user,data.cpf());
        deliveryPersonRepository.save(newDeliveryPerson);
        return ApiResponse.success(newDeliveryPerson, "Entregador criado com sucesso");


    }

    @Transactional
    public ApiResponse getAllDeliveryPerson(){

        List<Object[]> deliveryPersons = deliveryPersonRepository.findAllWithUserData();


        List<DeliveryPersonResponseDTO> deliveryPersonDtos = new ArrayList<>();


        for (Object[] row : deliveryPersons) {
            UUID id = UUID.fromString(row[0].toString());
            String cpf = (String) row[1];
            String email = (String) row[2];
            String name = (String) row[3];
            String telephone = (String) row[4];
            String role = (String) row[5];

            deliveryPersonDtos.add(new DeliveryPersonResponseDTO(id, cpf,role, name, email, telephone));
        }

        return ApiResponse.success(deliveryPersonDtos, "Todos os entregadores");

    }

    public ApiResponse getByIdDeliveryPerson(UUID id) {
        var deliveryPersonOpt = deliveryPersonRepository.findById(id);

        if (deliveryPersonOpt.isPresent()) {
            var deliveryPerson = deliveryPersonOpt.get();
            var userOpt = userRepository.findById(deliveryPerson.getUserId());

            if (userOpt.isPresent()) {
                var user = userOpt.get();

                DeliveryPersonResponseDTO deliveryPersonResponseDTO = new DeliveryPersonResponseDTO(
                        deliveryPerson.getId(),
                        deliveryPerson.getCpf(),
                        user.getRole().toString(),
                        user.getName(),
                        user.getEmail(),
                        user.getTelephone()
                );

                return ApiResponse.success(deliveryPersonResponseDTO, "Entregador encontrado");
            } else {
                return ApiResponse.error("Usuário associado não encontrado");
            }
        }

        return ApiResponse.error("Entregador não encontrado");
    }







}


