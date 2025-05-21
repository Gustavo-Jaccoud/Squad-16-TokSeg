package com.tokseg.storage.controllers.deliveryPerson;

import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.DeliveryPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/deliveryperson")
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonService deliveryPersonService;
    @PostMapping()
    public ResponseEntity createDeliveryPerson(@RequestBody @Valid DeliveryPersonDTO data){

        ApiResponse response = deliveryPersonService.createDeliveryPerson(data);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);


    }
    @GetMapping()
    public ResponseEntity getAllDeliveryPerson(){
        return ResponseEntity.status(HttpStatus.OK).body(deliveryPersonService.getAllDeliveryPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdDeliveryPerson(@PathVariable UUID id){
        var response = deliveryPersonService.getByIdDeliveryPerson(id);
        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
