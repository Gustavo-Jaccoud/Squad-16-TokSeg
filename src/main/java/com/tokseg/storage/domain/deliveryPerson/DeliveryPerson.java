package com.tokseg.storage.domain.deliveryPerson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Table(name = "delivery_person")
@Entity(name = "delivery_person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"user"})
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonProperty("userId")
    public UUID getUserId() {
        return user != null ? user.getId() : null;
    }

    @Column(nullable = false)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryPerson")
    private List<DeliveryPackage> deliveryPackages;

    public DeliveryPerson(User user, String cpf) {
        this.user = user;
        this.cpf = cpf;
    }
}
