package com.tokseg.storage.controllers.deliveryPackage;

import com.tokseg.storage.domain.deliveryPackage.DTOs.DeliveryPackageDTO;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.DeliveryPackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deliverypackage")
public class DeliveryPackageController {
    @Autowired
    DeliveryPackageService deliveryPackageService;

    @PostMapping
    public ResponseEntity createDeliveryPackage(@RequestBody @Valid DeliveryPackageDTO data){

        ApiResponse response = deliveryPackageService.createDeliveryPackage(data);

        if (response.data() != null)
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body(response);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @GetMapping()
    public ResponseEntity getAllDeliveryPackage(){
        return ResponseEntity.status(HttpStatus.OK).body(deliveryPackageService.getAllDeliveryPackage());
    }
}
