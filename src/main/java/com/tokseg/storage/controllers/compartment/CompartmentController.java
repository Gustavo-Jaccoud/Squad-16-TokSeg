package com.tokseg.storage.controllers.compartment;

import com.tokseg.storage.domain.compartment.DTOs.CompartmentDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.CompartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/compartment")
public class CompartmentController {

    @Autowired
    CompartmentService compartmentService;

    @PostMapping
    public ResponseEntity createCompartment(@RequestBody @Valid CompartmentDTO data){

        ApiResponse response = compartmentService.createCompartment(data);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @GetMapping
    public ResponseEntity getAllCompartment(){
        return ResponseEntity.status(HttpStatus.OK).body(compartmentService.getAllCompartment());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdCompartment(@PathVariable UUID id){
        var response = compartmentService.getByIdCompartment(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("getByIdCabinet/{id}")
    public ResponseEntity getByIdCabinet(@PathVariable UUID id){
        var response = compartmentService.getByIdCabinet(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCompartment(@PathVariable UUID id, @RequestBody @Valid CompartmentDTO data){
        var response = compartmentService.updateCompartment(id, data);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompartment(@PathVariable UUID id){
        ApiResponse response = compartmentService.deleteCompartment(id);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
