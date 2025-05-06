package com.tokseg.storage.controllers.deliveryPerson;

import com.tokseg.storage.domain.condominium.DTOs.CondominiumDTO;
import com.tokseg.storage.domain.deliveryPerson.DTOs.DeliveryPersonDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.DeliveryPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("deliveryperson")
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
}
