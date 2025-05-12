package com.tokseg.storage.domain.apartment;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.block.Block;
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
@JsonIgnoreProperties({"user","block"})
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Block block;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User user;

    @JsonProperty("blockId")
    public UUID getBlockId() {
        return block != null ? block.getId() : null;
    }

    @JsonProperty("userId")
    public UUID getUserId() {
        return user != null ? user.getId() : null;
    }
    private String apartmentNumber;

    public Apartment(Block block, User user, String apartmentNumber) {
        this.block = block;
        this.user = user;
        this.apartmentNumber = apartmentNumber;
    }

}
