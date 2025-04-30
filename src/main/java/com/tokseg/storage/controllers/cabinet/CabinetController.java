package com.tokseg.storage.controllers.cabinet;

import com.tokseg.storage.domain.cabinet.DTOs.CabinetDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.CabinetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
