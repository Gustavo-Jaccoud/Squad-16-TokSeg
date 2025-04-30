package com.tokseg.storage.services;

import com.tokseg.storage.domain.block.Block;
import com.tokseg.storage.domain.block.DTOs.BlockDTO;
import com.tokseg.storage.repositories.BlockRepository;
import com.tokseg.storage.repositories.CondominiumRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class BlockService {
    @Autowired
    BlockRepository blockRepository;

    @Autowired
    CondominiumRepository condominiumRepository;
    public ApiResponse createBlock(BlockDTO data){

        if (condominiumExists(data.condominiumId())) {

            Block newBlock = new Block(data.name(), data.condominiumId());
            blockRepository.save(newBlock);
            return ApiResponse.success(newBlock, "Bloco criado com sucesso");
        }
        return ApiResponse.error("Condominio não encontrado");

    }

    public ApiResponse getAllBlock(){
        return ApiResponse.success(blockRepository.findAll(), "Todos os blocos");
    }

    public ApiResponse getByIdBlock(UUID id){
        var response = blockRepository.findById(id);
        if (response.isPresent()) {
            return ApiResponse.success(response,"Bloco encontrado");
        }
        return ApiResponse.error("Bloco não encontrado");
    }
    public ApiResponse getByIdCondominium(UUID id){

        if (condominiumExists(id)) {
            var response = blockRepository.findByCondominiumId(id) ;
            return ApiResponse.success(response,"Todos os blocos desse condominio");
        }
        return ApiResponse.error("Condomínio não encontrado");
    }

    public ApiResponse updateBlock(UUID id , BlockDTO data){
        var block = blockRepository.findById(id);
        if (block.isEmpty()){
            return ApiResponse.error("Bloco não encontrado");
        }
        if(condominiumExists(data.condominiumId())){
            Block  dataUpdateBlock = block.get();
             dataUpdateBlock.setName(data.name());
             dataUpdateBlock.setCondominiumId(data.condominiumId());
             dataUpdateBlock = blockRepository.save( dataUpdateBlock);
            return ApiResponse.success( dataUpdateBlock,"Bloco atualizado com sucesso");
        }
        return ApiResponse.error("Condominio não encontrado");
    }

   public ApiResponse deleteBlock(UUID id){
        var block = blockRepository.findById(id);
        if (block.isPresent()){
            blockRepository.delete(block.get());
            return ApiResponse.success(null,"Bloco deletado com sucesso");
        }
       return ApiResponse.error("Bloco não encontrado");

   }

    private boolean condominiumExists(UUID id){
        return condominiumRepository.findById(id).isPresent();
    }

}
