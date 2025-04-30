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


    private boolean condominiumExists(UUID id){
        return condominiumRepository.findById(id).isPresent();
    }
}
