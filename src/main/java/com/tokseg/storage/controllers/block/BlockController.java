package com.tokseg.storage.controllers.block;

import com.tokseg.storage.domain.block.DTOs.BlockDTO;
import com.tokseg.storage.domain.condominium.DTOs.CondominiumDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.BlockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
