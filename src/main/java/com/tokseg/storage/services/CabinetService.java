package com.tokseg.storage.services;

import com.tokseg.storage.domain.block.Block;
import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.cabinet.DTOs.CabinetDTO;
import com.tokseg.storage.repositories.CabinetRepository;
import com.tokseg.storage.repositories.CondominiumRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CabinetService {
    @Autowired
    CabinetRepository cabinetRepository;

    @Autowired
    CondominiumRepository condominiumRepository;

    public ApiResponse createCabinet(CabinetDTO data){


        if (condominiumExists(data.condominiumId())) {

            Cabinet newCabinet = new Cabinet(data.condominiumId(), data.location(), data.status());
            cabinetRepository.save(newCabinet);
            return ApiResponse.success(newCabinet, "Armario criado com sucesso");
        }
        return ApiResponse.error("Condominio não encontrado");

    }

    public ApiResponse getAllCabinet(){
        return ApiResponse.success(cabinetRepository.findAll(), "Todos os armarios");
    }

    public ApiResponse getByIdCabinet(UUID id){
        var response = cabinetRepository.findById(id);
        if (response.isPresent()) {
            return ApiResponse.success(response,"Armario encontrado");
        }
        return ApiResponse.error("Armario não encontrado");
    }
    public ApiResponse getByIdCondominium(UUID id){

        if (condominiumExists(id)) {
            var response = cabinetRepository.findByCondominiumId(id) ;
            return ApiResponse.success(response,"Todos os Armarios desse condominio");
        }
        return ApiResponse.error("Condomínio não encontrado");
    }


    private boolean condominiumExists(UUID id){
        return condominiumRepository.findById(id).isPresent();
    }
}
