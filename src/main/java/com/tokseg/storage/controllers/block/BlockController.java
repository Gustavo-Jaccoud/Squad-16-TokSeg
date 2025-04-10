package com.tokseg.storage.controllers.block;

import com.tokseg.storage.domain.block.DTOs.BlockDTO;
import com.tokseg.storage.domain.condominium.DTOs.CondominiumDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.BlockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("block")
public class BlockController {
    @Autowired
    BlockService blockService;

    @PostMapping()
    public ResponseEntity createBlock(@RequestBody @Valid BlockDTO data){

        ApiResponse response = blockService.createBlock(data);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @GetMapping()
    public ResponseEntity getAllBlock(){
        return ResponseEntity.status(HttpStatus.OK).body(blockService.getAllBlock());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdBlock(@PathVariable UUID id){
        var response = blockService.getByIdBlock(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("getByIdCondominium/{id}")
    public ResponseEntity getByIdCondominium(@PathVariable UUID id){
        var response = blockService.getByIdCondominium(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBlock(@PathVariable UUID id, @RequestBody @Valid BlockDTO data){
        var response = blockService.updateBlock(id, data);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBlock(@PathVariable UUID id){
        ApiResponse response = blockService.deleteBlock(id);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
