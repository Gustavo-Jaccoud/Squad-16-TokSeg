package com.tokseg.storage.controllers.condominium;

import com.tokseg.storage.domain.condominium.DTOs.CondominiumDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.CondominiumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.hibernate.validator.constraints.UUID;

@RestController()
@RequestMapping("condominium")
public class CondominiumController {
    @Autowired
    CondominiumService condominiumService;

    @GetMapping()
    public ResponseEntity getAllCondominium(){
        return ResponseEntity.status(HttpStatus.OK).body(condominiumService.getAllCondominium());
    }

    @PostMapping()
    public ResponseEntity createCondominium(@RequestBody @Valid CondominiumDTO data){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .body(condominiumService.createCondominium(data));

    }

    @GetMapping("/{id}")
        public ResponseEntity getByIdCondominium(@PathVariable UUID id){
            var response = condominiumService.GetByIdCondominium(id);
            if (response.data() != null)
                return ResponseEntity.status(HttpStatus.OK).body(response);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    @PutMapping("{id}")
        public ResponseEntity updateCondominium(@PathVariable UUID id, @RequestBody CondominiumDTO data){

        var response = condominiumService.updateCondominio(id, data);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("{id}")
        public ResponseEntity deleteCondominium(@PathVariable UUID id){

        ApiResponse response = condominiumService.deleteCondominium(id);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);


    }

}
