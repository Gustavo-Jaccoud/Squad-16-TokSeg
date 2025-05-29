package com.tokseg.storage.services.email;

import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailContentBuilder {

    public String buildDeliveryNotification(DeliveryPackage deliveryPackage) {



        String nameResident = deliveryPackage.getApartment().getOwner().getName();
        String nameDeliveryPerson = deliveryPackage.getDeliveryPerson().getUser().getName();
        Compartment compartment = deliveryPackage.getCompartment();
        String compartmentName = compartment.getName();
        Cabinet cabinet = compartment.getCabinet();
        String cabinetName = cabinet.getName();
        String cabinetLocation = cabinet.getLocation();
        LocalDateTime deliveryDateTime = deliveryPackage.getDeliveryDatetime();
        LocalDateTime maxPickDateTime = deliveryPackage.getMaxPickupDatetime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        try {
            String body = Files.readString(Paths.get("src/main/resources/emails/delivery-notification.html"));
            return String.format(
                    body,
                    nameResident,
                    nameDeliveryPerson,
                    deliveryDateTime.format(formatter),
                    cabinetName,
                    cabinetLocation,
                    compartmentName,
                    maxPickDateTime.format(formatter)
            );

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }
    }

    public String buildPasswordRecoverNotification(String name, String password){
        try {
            String body = Files.readString(Paths.get("src/main/resources/emails/password-recover-notification.html"));
            return String.format(body,name, password);
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }
    }

    public  String buildWelcomeNotification(String name){
        try {
            String body = Files.readString(Paths.get("src/main/resources/emails/welcome-notification.html"));
            return String.format(body,name);
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }

    }
}
