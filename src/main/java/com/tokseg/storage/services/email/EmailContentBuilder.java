package com.tokseg.storage.services.email;

import com.tokseg.storage.domain.cabinet.Cabinet;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("emails/delivery-notification.html");
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de template não encontrado.");
            }
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("emails/password-recover-notification.html");
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de template não encontrado.");
            }
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return String.format(body,name, password);
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }
    }

    public  String buildWelcomeNotification(String name){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("emails/welcome-notification.html");
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de template não encontrado.");
            }
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return String.format(body,name);
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }

    }

    public String buildPickedUpNotification(String name, LocalDateTime datetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("emails/picked-up-notification.html");
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de template não encontrado.");
            }
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return String.format(body,name, datetime.format(formatter));
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }
    }

    public String buildRetrievedByStaffNotification(String name,LocalDateTime datetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("emails/retrieved-by-staff-notification.html");
            if (inputStream == null) {
                throw new RuntimeException("Arquivo de template não encontrado.");
            }
            String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return String.format(body,name, datetime.format(formatter));
        }
        catch (IOException e) {
            throw new RuntimeException("Erro ao ler template de e-mail", e);
        }
    }
}
