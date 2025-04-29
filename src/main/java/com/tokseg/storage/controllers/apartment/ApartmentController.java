package com.tokseg.storage.controllers.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tokseg.storage.domain.apartment.DTOs.ApartmentDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.ApartmentService;

import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    @Autowired
    ApartmentService apartmentService;

    @PostMapping()
    public ResponseEntity createApartment(@RequestBody @Valid ApartmentDTO data) {
        ApiResponse response = apartmentService.createApartment(data);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
    @GetMapping()
    public ResponseEntity getAllApartment(){
        return ResponseEntity.status(HttpStatus.OK).body(apartmentService.getAllApartment());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdBlock(@PathVariable UUID id){
        var response = apartmentService.getByIdApartment(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("getByIdBlock/{id}")
    public ResponseEntity getByIdblock(@PathVariable UUID id){
        var response = apartmentService.getByIdBlock(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("getByIdUser/{id}")
    public ResponseEntity getByIdCondominium(@PathVariable UUID id){
        var response = apartmentService.getByIdUser(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
