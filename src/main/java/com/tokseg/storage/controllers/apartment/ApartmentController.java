package com.tokseg.storage.controllers.apartment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokseg.storage.domain.apartment.DTOs.ApartmentDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.ApartmentService;

import jakarta.validation.Valid;

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
}
