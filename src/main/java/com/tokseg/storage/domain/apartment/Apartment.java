package com.tokseg.storage.domain.apartment;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.block.Block;
import com.tokseg.storage.domain.deliveryPackage.DeliveryPackage;
import com.tokseg.storage.domain.user.User;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Table(name = "apartment")
@Entity(name = "apartment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"owner","block"})
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Block block;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User owner;

    @JsonProperty("blockId")
    public UUID getBlockId() {
        return block != null ? block.getId() : null;
    }

    @JsonProperty("userId")
    public UUID getUserId() {
        return owner != null ? owner.getId() : null;
    }
    @Column(nullable = false)
    private String apartmentNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryPackage> deliveryPackages;

    public Apartment(Block block, User owner, String apartmentNumber) {
        this.block = block;
        this.owner = owner;
        this.apartmentNumber = apartmentNumber;
    }

}
