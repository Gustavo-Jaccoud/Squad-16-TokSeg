package com.tokseg.storage.services;

import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.cabinet.Cabinet;
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

import java.sql.Date;
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

    private void sendEmailDeliver2y(DeliveryPackage deliveryPackage) {
        String nameResident = deliveryPackage.getApartment().getUser().getName();
        String nameDeliveryPerson = deliveryPackage.getDeliveryPerson().getUser().getName();
        LocalDateTime deliveryDateTime = deliveryPackage.getDeliveryDatetime();
        LocalDateTime maxPickDateTime = deliveryPackage.getMaxPickupDatetime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

        String to = deliveryPackage.getApartment().getUser().getEmail();
        String subject = "Aviso de entrega: sua encomenda foi recebida";
        String body = String.format(
                """
                        %s, você tem uma nova encomenda que foi entregue por %s
                        às %s e você tem até %s para remover a encomenda dos armários
                        do condomínio utilizando sua senha cadastrada no aplicativo. Após essa data e horário, sua encomenda pode ser removida pela administração.
                        """,
                nameResident,
                nameDeliveryPerson,
                deliveryDateTime.format(formatter),
                maxPickDateTime.format(formatter));

        emailServices.sendEmail(new EmailDTO(to, subject, body, true));

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
                        <html>
                        <body style="font-family: Arial, sans-serif; background-color: #f4f6f8; padding: 20px;">
                            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                                <div style="text-align: center;">
                                    <h2 style="color: #004aad;">📦 Sua encomenda chegou!</h2>
                                    <p style="font-size: 18px;"><strong>Tokseg | Storage</strong></p>
                                </div>

                                <p>Olá, <strong style="color: #004aad;">%s</strong>,</p>

                                <p>Você recebeu uma nova encomenda entregue por <strong>%s</strong> no dia <strong>%s</strong>.</p>

                                <p>Você tem até <strong>%s</strong> para retirar sua encomenda nos armários inteligentes do condomínio usando sua senha cadastrada no aplicativo <strong>Storage</strong>.</p>

                                <p style="color: #b30000;"><em>Após esse prazo, a encomenda poderá ser recolhida pela administração do condomínio.</em></p>

                                <div style="text-align: center; margin-top: 30px;">
                                    <a href="https://app.storage.tokseg.com" style="background-color: #004aad; color: white; padding: 12px 24px; text-decoration: none; border-radius: 6px; font-weight: bold;">
                                        Acessar o Storage
                                    </a>
                                </div>

                                <hr style="margin-top: 40px;">

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
