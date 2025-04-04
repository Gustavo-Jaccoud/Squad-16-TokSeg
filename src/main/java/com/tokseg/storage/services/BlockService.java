package com.tokseg.storage.services;

import com.tokseg.storage.domain.block.Block;
import com.tokseg.storage.domain.block.DTOs.BlockDTO;
import com.tokseg.storage.repositories.BlockRepository;
import com.tokseg.storage.repositories.CondominiumRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BlockService {
    @Autowired
    BlockRepository blockRepository;

    @Autowired
    CondominiumRepository condominiumRepository;
    public ApiResponse createBlock(BlockDTO data){

        if (condominiumRepository.findById(data.condominium_id()).isPresent()) {

            Block newBlock = new Block(data.name(), data.condominium_id());
            blockRepository.save(newBlock);
            return ApiResponse.success(null, "Bloco criado com sucesso");
        }
        return ApiResponse.error("Condominio não encontrado");

    }
}
