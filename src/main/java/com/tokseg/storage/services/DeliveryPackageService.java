package com.tokseg.storage.services;

import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.compartment.Compartment;
import com.tokseg.storage.domain.deliveryPackage.DTOs.DeliveryPackageDTO;
import com.tokseg.storage.domain.deliveryPackage.DTOs.PickUpDeliveryPackageDTO;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.deliveryPackage.PackageStatus;
import com.tokseg.storage.domain.deliveryPerson.DeliveryPerson;
import com.tokseg.storage.domain.notification.DTOs.NotificationDTO;
import com.tokseg.storage.domain.notification.NotificationStatus;
import com.tokseg.storage.domain.notification.NotificationType;
import com.tokseg.storage.domain.user.User;
import com.tokseg.storage.domain.user.UserRole;
import com.tokseg.storage.repositories.*;
import com.tokseg.storage.response.ApiResponse;
import com.tokseg.storage.services.email.EmailContentBuilder;
import com.tokseg.storage.services.email.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.Optional;

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

    @Autowired
    EmailContentBuilder emailContentBuilder;

    @Autowired
    NotificationService notificationService;

    @Autowired
    UserRepository userRepository;

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

    public ApiResponse pickUpDeliveryPackage(PickUpDeliveryPackageDTO data) {

        String password = new BCryptPasswordEncoder().encode(data.password());
        Optional<DeliveryPackage> optionalPackage =
                deliveryPackageRepository.findTopByCompartment_IdOrderByDeliveryDatetimeDesc(data.compartmentId());

        if (optionalPackage.isEmpty()) {
            return ApiResponse.error("Nenhuma encomenda encontrada para este compartimento.");
        }

        DeliveryPackage deliveryPackage = optionalPackage.get();
        User owner = deliveryPackage.getApartment().getOwner();


        boolean isOwner = owner.getUsername().equals(data.username()) && owner.getPassword().equals(password);

        System.out.println(owner.getUsername());
        System.out.println(data.username());
        System.out.println(owner.getPassword());
        System.out.println(password);

        if (!isOwner) {

            User admin = userRepository.findByEmail(data.username());
            if (admin != null && admin.getPassword().equals(password) && admin.getRole() == UserRole.ADMIN) {

                if (deliveryPackage.getDeliveryDatetime().isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))) {
                    deliveryPackage.setPickupDatetime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                    deliveryPackage.setPickedUpBy(admin);
                    deliveryPackage.setPackageStatus(PackageStatus.PICKED_UP);
                    deliveryPackageRepository.save(deliveryPackage);
                    return ApiResponse.success(deliveryPackage.getId(), "Encomenda retirada pela administração.");
                } else {
                    return ApiResponse.error("A encomenda ainda está dentro do prazo, apenas o morador pode retirar.");
                }
            }

            return ApiResponse.error("Credenciais inválidas.");
        }


        deliveryPackage.setPickupDatetime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        deliveryPackage.setPickedUpBy(owner);
        deliveryPackage.setPackageStatus(PackageStatus.PICKED_UP);
        deliveryPackageRepository.save(deliveryPackage);

        return ApiResponse.success(deliveryPackage.getId(), "Encomenda retirada com sucesso pelo proprietário.");
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
        String to = deliveryPackage.getApartment().getOwner().getEmail();
        String subject = "Tokseg | Storage - Você recebeu uma nova encomenda!";
        String body = emailContentBuilder.buildDeliveryNotification(deliveryPackage);

        boolean email = emailServices.sendEmail(to, subject, body, true);

        notificationService.createNotification(new NotificationDTO(deliveryPackage,
                email ? NotificationStatus.SENT : NotificationStatus.FAILED,
                NotificationType.PENDING_PICKUP));
    }

}
