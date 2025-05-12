package com.tokseg.storage.services;

import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.compartment.DTOs.CompartmentDTO;
import com.tokseg.storage.repositories.CabinetRepository;
import com.tokseg.storage.repositories.CompartmentRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompartmentService {
    @Autowired
    CompartmentRepository compartmentRepository;

    @Autowired
    CabinetRepository cabinetRepository;



    public ApiResponse createCompartment (CompartmentDTO data){


        if (cabinetExists(data.cabinetId())) {

            Cabinet cabinet = cabinetRepository.findById(data.cabinetId()).get();
            Compartment newCompartment = new Compartment(cabinet, data.name(), data.size(), data.isOccupied());
            compartmentRepository.save(newCompartment);
            return ApiResponse.success(newCompartment, "Compartimento criado com sucesso");
        }
        return ApiResponse.error("Armario não encontrado");

    }
    public ApiResponse getAllCompartment(){
        return ApiResponse.success(compartmentRepository.findAll(), "Todos os compartimentos");
    }

    public ApiResponse getByIdCompartment(UUID id){
        var response = compartmentRepository.findById(id);
        if (response.isPresent()) {
            return ApiResponse.success(response,"Compartimento encontrado");
        }
        return ApiResponse.error("Compartimento não encontrado");
    }
    public ApiResponse getByIdCabinet(UUID id){

        if (cabinetExists(id)) {
            var response = compartmentRepository.findByCabinet_Id(id) ;
            return ApiResponse.success(response,"Todos os compartimentos desse armario");
        }
        return ApiResponse.error("Armario não encontrado");
    }

    public ApiResponse updateCompartment(UUID id , CompartmentDTO data){
        var compartment = compartmentRepository.findById(id);
        if (compartment.isEmpty()){
            return ApiResponse.error("Compartimento não encontrado");
        }
        if(cabinetExists(data.cabinetId())){
            Compartment dataUpdateCompartment = compartment.get();
            Cabinet cabinet = cabinetRepository.findById(data.cabinetId()).get();
            dataUpdateCompartment.setName(data.name());
            dataUpdateCompartment.setCabinet(cabinet);
            dataUpdateCompartment.setSize(data.size());
            dataUpdateCompartment.setOccupied(data.isOccupied());
            dataUpdateCompartment = compartmentRepository.save( dataUpdateCompartment);
            return ApiResponse.success( dataUpdateCompartment,"Compartimento atualizado com sucesso");
        }
        return ApiResponse.error("Armario não encontrado");
    }

    public ApiResponse deleteCompartment(UUID id){
        var compartment = compartmentRepository.findById(id);
        if (compartment.isPresent()){
            compartmentRepository.delete(compartment.get());
            return ApiResponse.success(null,"Compartimento deletado com sucesso");
        }
        return ApiResponse.error("Compartimento não encontrado");

    }

    private boolean cabinetExists(UUID id){
        return cabinetRepository.findById(id).isPresent();
    }
}
