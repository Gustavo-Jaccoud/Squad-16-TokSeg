package com.tokseg.storage.services;

import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.deliveryPackage.DTOs.DeliveryPackageDTO;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.domain.email.DTOs.EmailDTO;
import com.tokseg.storage.repositories.ApartmentRepository;
import com.tokseg.storage.repositories.CompartmentRepository;
import com.tokseg.storage.repositories.DeliveryPackageRepository;
import com.tokseg.storage.repositories.DeliveryPersonRepository;
import com.tokseg.storage.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    EmailServices emailServices;

    public ApiResponse createDeliveryPackage(DeliveryPackageDTO data) {

        if (!deliveryPersonExists(data.deliveryPersonId())) {
            return ApiResponse.error("Entregador não encontrado");
        }
        if (!apartmentExists(data.apartmentId())) {
            return ApiResponse.error("Apartamento não encontrado");
        }
        if (!compartmentExists(data.compartmentId())) {
            return ApiResponse.error("Compartimento não encontrado");
        }
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(data.deliveryPersonId()).get();
        Compartment compartment = compartmentRepository.findById(data.compartmentId()).get();
        Apartment apartment = apartmentRepository.findById(data.apartmentId()).get();
        DeliveryPackage newDeliveryPackage = new DeliveryPackage(deliveryPerson, compartment, apartment);

        deliveryPackageRepository.save(newDeliveryPackage);

        sendEmailDelivery(newDeliveryPackage);

        return ApiResponse.success(newDeliveryPackage, "Entrega criada com sucesso");

    }

    public ApiResponse getAllDeliveryPackage() {
        return ApiResponse.success(deliveryPackageRepository.findAll(), "Todos as entregas");
    }

    private boolean deliveryPersonExists(UUID id) {
        return deliveryPersonRepository.findById(id).isPresent();
    }

    private boolean apartmentExists(UUID id) {
        return apartmentRepository.findById(id).isPresent();
    }

    private boolean compartmentExists(UUID id) {
        return compartmentRepository.findById(id).isPresent();
    }

    private void sendEmailDelivery(DeliveryPackage deliveryPackage) {
        String nameResident = deliveryPackage.getApartment().getUser().getName();
        String nameDeliveryPerson = deliveryPackage.getDeliveryPerson().getUser().getName();
        LocalDateTime deliveryDateTime = deliveryPackage.getDeliveryDatetime();
        LocalDateTime maxPickDateTime = deliveryPackage.getMaxPickupDatetime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

        String to = deliveryPackage.getApartment().getUser().getEmail();
        String subject = "Tokseg | Storage - Você recebeu uma nova encomenda!";

        String body = String.format(
                """
                        <!DOCTYPE html>
                        <html lang="pt_br">
                        <head>
                            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        </head>
                        
                        <body style="font-family: Arial, sans-serif; background-color: #f4f6f8; padding: 20px;">
                            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                                <div style="text-align: center;">
                                    <h2 style="color: #18a758;">📦 Sua encomenda chegou!</h2>
                                    <p style="font-size: 18px;"><b>Tokseg | Storage</b></p>
                                </div>
                        
                                <p>Olá, <b style="color: #18a758;">%s</b></p>
                        
                                <p>Você recebeu uma nova encomenda entregue por <b>%s</b> no dia <b>%s</b>.</p>
                        
                                <p>Você tem até <b>%s</b> para retirar sua encomenda nos armários inteligentes do condomínio usando
                                    sua senha cadastrada no aplicativo <b>Storage</b>.</p>
                        
                                <p style="color: #b30000;"><em>Após esse prazo, a encomenda poderá ser recolhida pela administração do
                                        condomínio.</em></p>
                        
                                <!-- LOGO COMO CARIMBO -->
                                <div style="text-align: center; margin-top: 20px;">
                                    <img src="https://tokseg.com/assets/logo-DZ4izHdq.png" alt="Tokseg Logo" style="width: 250px; opacity: 0.9;">
                                </div>
                                <hr style="margin-top: 20px;">
                  
                                <p style="font-size: 13px; color: #777; text-align: center;">
                                    © Tokseg | Storage – Segurança e praticidade para o seu condomínio.
                                </p>
                            </div>
                        </body>
                        
                        </html>
                        
                        """,
                nameResident,
                nameDeliveryPerson,
                deliveryDateTime.format(formatter),
                maxPickDateTime.format(formatter));

        EmailDTO email = new EmailDTO(to, subject, body, true);
        emailServices.sendEmail(email);
    }

}
