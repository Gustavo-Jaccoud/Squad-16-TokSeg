package com.tokseg.storage.services;


import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonDTO;
import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonResponseDTO;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.domain.user.DTOs.RegisterDTO;
import com.tokseg.storage.domain.user.User;
import com.tokseg.storage.domain.user.UserRole;
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
        var user = authService.createUserIfEmailNotExists(new RegisterDTO(
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

        List<DeliveryPerson> deliveryPersons = deliveryPersonRepository.findAll();


        List<DeliveryPersonResponseDTO> deliveryPersonDtos = new ArrayList<>();


        for (DeliveryPerson deliveryPerson : deliveryPersons) {
            UUID id = deliveryPerson.getId();
            String cpf = deliveryPerson.getCpf();
            User user = deliveryPerson.getUser();
            String email = user.getEmail();
            String name = user.getName();
            String telephone = user.getTelephone();
            UserRole role = user.getRole();

            deliveryPersonDtos.add(new DeliveryPersonResponseDTO(id, cpf,role, name, email, telephone));
        }

        return ApiResponse.success(deliveryPersonDtos, "Todos os entregadores");

    }

    public ApiResponse getByIdDeliveryPerson(UUID id) {
        var deliveryPersonOpt = deliveryPersonRepository.findById(id);

        if (deliveryPersonOpt.isPresent()) {
            var deliveryPerson = deliveryPersonOpt.get();

                var user = deliveryPerson.getUser();

                DeliveryPersonResponseDTO deliveryPersonResponseDTO = new DeliveryPersonResponseDTO(
                        deliveryPerson.getId(),
                        deliveryPerson.getCpf(),
                        user.getRole(),
                        user.getName(),
                        user.getEmail(),
                        user.getTelephone()
                );

                return ApiResponse.success(deliveryPersonResponseDTO, "Entregador encontrado");
            }

        return ApiResponse.error("Entregador não encontrado");
    }







}


