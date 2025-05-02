package com.tokseg.storage.controllers.cabinet;

import com.tokseg.storage.domain.block.DTOs.BlockDTO;
import com.tokseg.storage.domain.cabinet.DTOs.CabinetDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.CabinetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cabinet")
public class CabinetController {
    @Autowired
    CabinetService cabinetService;

    @PostMapping
    public ResponseEntity createCabinet(@RequestBody @Valid CabinetDTO data){

        ApiResponse response = cabinetService.createCabinet(data);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity getAllCabinet(){
        return ResponseEntity.status(HttpStatus.OK).body(cabinetService.getAllCabinet());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdCabinet(@PathVariable UUID id){
        var response = cabinetService.getByIdCabinet(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("getByIdCondominium/{id}")
    public ResponseEntity getByIdCondominium(@PathVariable UUID id){
        var response = cabinetService.getByIdCondominium(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCabinet(@PathVariable UUID id, @RequestBody @Valid CabinetDTO data){
        var response = cabinetService.updateCabinet(id, data);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCabinet(@PathVariable UUID id){
        ApiResponse response = cabinetService.deleteCabinet(id);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
