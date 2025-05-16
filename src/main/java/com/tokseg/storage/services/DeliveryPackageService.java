package com.tokseg.storage.services;

import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.deliveryPackage.DTOs.DeliveryPackageDTO;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.repositories.ApartmentRepository;
import com.tokseg.storage.repositories.CompartmentRepository;
import com.tokseg.storage.repositories.DeliveryPackageRepository;
import com.tokseg.storage.repositories.DeliveryPersonRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeliveryPackageService {

    @Autowired
    DeliveryPackageRepository deliveryPackageRepository;

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    CompartmentRepository compartmentRepository;

    public ApiResponse createDeliveryPackage(DeliveryPackageDTO data){

        if(!deliveryPersonExists(data.deliveryPersonId())){
            return ApiResponse.error("Entregador não encontrado");
        }
        if(!apartmentExists(data.apartmentId())){
            return ApiResponse.error("Apartamento não encontrado");
        }
        if(!compartmentExists(data.compartmentId())){
            return ApiResponse.error("Compartimento não encontrado");
        }
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(data.deliveryPersonId()).get();
        Compartment compartment = compartmentRepository.findById(data.compartmentId()).get();
        Apartment apartment = apartmentRepository.findById(data.apartmentId()).get();
        DeliveryPackage newDeliveryPackage = new DeliveryPackage(deliveryPerson,compartment,apartment);

        deliveryPackageRepository.save(newDeliveryPackage);

        return ApiResponse.success(newDeliveryPackage,"Entrega criada com sucesso");

    }

    public ApiResponse getAllDeliveryPackage(){
        return ApiResponse.success(deliveryPackageRepository.findAll(), "Todos as entregas");
    }



    private boolean deliveryPersonExists(UUID id){
        return deliveryPersonRepository.findById(id).isPresent();
    }
    private boolean apartmentExists(UUID id){
        return apartmentRepository.findById(id).isPresent();
    }
    private boolean compartmentExists(UUID id){
        return compartmentRepository.findById(id).isPresent();
    }

}
