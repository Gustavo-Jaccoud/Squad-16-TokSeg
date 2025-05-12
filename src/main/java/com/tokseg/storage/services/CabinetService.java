package com.tokseg.storage.services;

import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.cabinet.DTOs.CabinetDTO;
import com.tokseg.storage.domain.condominium.Condominium;
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
            Condominium condominium = condominiumRepository.findById(data.condominiumId()).get();
            Cabinet newCabinet = new Cabinet(condominium, data.name(), data.location(), data.status());
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
            var response = cabinetRepository.findByCondominium_Id(id) ;
            return ApiResponse.success(response,"Todos os Armarios desse condominio");
        }
        return ApiResponse.error("Condomínio não encontrado");
    }

    public ApiResponse updateCabinet(UUID id , CabinetDTO data){
        var cabinet = cabinetRepository.findById(id);
        if (cabinet.isEmpty()){
            return ApiResponse.error("Armario não encontrado");
        }
        if(condominiumExists(data.condominiumId())){
            Cabinet  dataUpdateCabinet = cabinet.get();
            Condominium condominium = condominiumRepository.findById(data.condominiumId()).get();
            dataUpdateCabinet.setName(data.name());
            dataUpdateCabinet.setCondominium(condominium);
            dataUpdateCabinet.setLocation(data.location());
            dataUpdateCabinet.setStatus(data.status());
            dataUpdateCabinet = cabinetRepository.save( dataUpdateCabinet);
            return ApiResponse.success( dataUpdateCabinet,"Armario atualizado com sucesso");
        }
        return ApiResponse.error("Condominio não encontrado");
    }

    public ApiResponse deleteCabinet(UUID id){
        var cabinet = cabinetRepository.findById(id);
        if (cabinet.isPresent()){
            cabinetRepository.delete(cabinet.get());
            return ApiResponse.success(null,"Armario deletado com sucesso");
        }
        return ApiResponse.error("Armario não encontrado");

    }

    private boolean condominiumExists(UUID id){
        return condominiumRepository.findById(id).isPresent();
    }
}
