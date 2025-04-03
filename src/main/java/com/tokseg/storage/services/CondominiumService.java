package com.tokseg.storage.services;

import com.tokseg.storage.domain.condominium.Condominium;
import com.tokseg.storage.domain.condominium.DTOs.CreateCondominiumDTO;
import com.tokseg.storage.repositories.CondominiumRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CondominiumService {

    @Autowired
    CondominiumRepository condominiumRepository;
    public ApiResponse CreateCondominium(CreateCondominiumDTO data){

        Condominium newCondominium = new Condominium(data.name(), data.address() , data.telephone());
        condominiumRepository.save(newCondominium);
        return ApiResponse.success(null, "Condominio criado com sucesso");
    }
}
