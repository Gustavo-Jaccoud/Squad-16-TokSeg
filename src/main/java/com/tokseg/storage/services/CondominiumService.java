package com.tokseg.storage.services;

import com.tokseg.storage.domain.condominium.Condominium;
import com.tokseg.storage.domain.condominium.DTOs.CondominiumDTO;
import com.tokseg.storage.repositories.CondominiumRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CondominiumService {

    @Autowired
    CondominiumRepository condominiumRepository;
    public ApiResponse createCondominium(CondominiumDTO data){

        Condominium newCondominium = new Condominium(data.name(), data.address() , data.telephone());
        condominiumRepository.save(newCondominium);
        return ApiResponse.success(null, "Condomínio criado com sucesso");
    }

    public ApiResponse getAllCondominium(){
        return ApiResponse.success(condominiumRepository.findAll(),"Todos os Condomínios");
    }

    public ApiResponse getByIdCondominium(UUID id){
        var response = condominiumRepository.findById(id);
        if (response.isPresent()) {
            return ApiResponse.success(response,"Condomínio encontrado");
        }
        return ApiResponse.error("Condomínio não encontrado");
    }

    public ApiResponse updateCondominio(UUID id, CondominiumDTO data){
        var condominio = condominiumRepository.findById(id);

        if (condominio.isEmpty()){
            return ApiResponse.error("Condomínio não encontrado");
        }

        Condominium dataUpdateCondominio = condominio.get();
        dataUpdateCondominio.setName(data.name());
        dataUpdateCondominio.setAddress(data.address());
        dataUpdateCondominio.setTelephone(data.telephone());

        dataUpdateCondominio = condominiumRepository.save(dataUpdateCondominio);

        return ApiResponse.success(dataUpdateCondominio,"Condomínio atualizado com sucesso");
    }

    public ApiResponse deleteCondominium(UUID id){
        var condominium = condominiumRepository.findById(id);
        if (condominium.isPresent()) {
            condominiumRepository.delete(condominium.get());
            return ApiResponse.success(null,"Condomínio deletado com sucesso");
        }
        return ApiResponse.error("Condomínio não encontrado");
    }
}
