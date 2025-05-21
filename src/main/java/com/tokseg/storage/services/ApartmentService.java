package com.tokseg.storage.services;

import java.util.UUID;

import com.tokseg.storage.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.apartment.DTOs.ApartmentDTO;
import com.tokseg.storage.domain.block.Block;
import com.tokseg.storage.repositories.ApartmentRepository;
import com.tokseg.storage.repositories.BlockRepository;
import com.tokseg.storage.repositories.UserRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApartmentService {
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse createApartment(ApartmentDTO data) {

        if (userExists(data.userId())) {

            if (blockExists(data.blockId())) {
                Block block = blockRepository.findById(data.blockId()).get();
                User user = userRepository.findById(data.userId()).get();

                Apartment newApartment = new Apartment(block,user, data.apartmentNumber());
                apartmentRepository.save(newApartment);
                return ApiResponse.success(newApartment, "Apartamento criado com sucesso");
            }

            return ApiResponse.error("Bloco não encontrado");
        }
        return ApiResponse.error("Usuário não encontrado");

    }
    @Transactional
    public ApiResponse getAllApartment(){
        return ApiResponse.success(apartmentRepository.findAll(), "Todos os apartamentos");
    }

    public ApiResponse getByIdApartment(UUID id){
        var response = apartmentRepository.findById(id);
        if (response.isPresent()) {
            return ApiResponse.success(response,"Apartamento encontrado");
        }
        return ApiResponse.error("Apartamento não encontrado");
    }

    public ApiResponse getByIdBlock(UUID id){

        if (blockExists(id)) {
            var response = apartmentRepository.findByBlock_Id(id) ;
            return ApiResponse.success(response,"Todos os apartementos desse bloco");
        }
        return ApiResponse.error("Bloco não encontrado");
    }

    public ApiResponse getByIdUser(UUID id){

        if (userExists(id)) {
            var response = apartmentRepository.findByOwnerId(id) ;
            return ApiResponse.success(response,"Apartamentos desse usuário");
        }
        return ApiResponse.error("Usuário não encontrado");
    }

    public ApiResponse updateApartment(UUID id , ApartmentDTO data){
        var apartment = apartmentRepository.findById(id);
        if (apartment.isEmpty()){
            return ApiResponse.error("Apartamento não encontrado");
        }
        if(blockExists(data.blockId())){
            if(userExists(data.userId())){

                Block block = blockRepository.findById(data.blockId()).get();
                User user = userRepository.findById(data.userId()).get();

                Apartment  dataUpdateApartment = apartment.get();
                dataUpdateApartment.setApartmentNumber(data.apartmentNumber());
                dataUpdateApartment.setBlock(block);
                dataUpdateApartment.setUser(user);
                dataUpdateApartment = apartmentRepository.save( dataUpdateApartment);
                return ApiResponse.success( dataUpdateApartment,"Apartamento atualizado com sucesso");
            }
            return ApiResponse.error("Usuário não encontrado");
        }
        return ApiResponse.error("Bloco não encontrado");
    }

    public ApiResponse deleteApartment(UUID id){
        var apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()){
            apartmentRepository.delete(apartment.get());
            return ApiResponse.success(null,"Apartamento deletado com sucesso");
        }
        return ApiResponse.error("Apartamento não encontrado");

    }

    private boolean blockExists(UUID id) {
        return blockRepository.findById(id).isPresent();
    }

    private boolean userExists(UUID id) {
        return userRepository.findById(id).isPresent();
    }

}
