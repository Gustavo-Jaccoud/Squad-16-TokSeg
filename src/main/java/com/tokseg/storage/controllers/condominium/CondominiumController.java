package com.tokseg.storage.controllers.condominium;

import com.tokseg.storage.domain.condominium.DTOs.CreateCondominiumDTO;
import com.tokseg.storage.services.CondominiumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("condominium")
public class CondominiumController {
    @Autowired
    CondominiumService condominiumService;

    @PostMapping()
    public ResponseEntity CreateCondominium(@RequestBody @Valid CreateCondominiumDTO data){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .body(condominiumService.CreateCondominium(data));

    }

}
