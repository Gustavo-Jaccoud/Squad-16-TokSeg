package com.tokseg.storage.domain.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.apartment.Apartment;
import com.tokseg.storage.domain.condominium.Condominium;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

@Entity(name = "block")
@Table(name = "block")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"condominium"})
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "condominium_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Condominium condominium;

    @Column(nullable = false)
    private String name;

    @JsonProperty("condominiumId")
    public UUID getCondominiumId() {
        return condominium != null ? condominium.getId() : null;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apartment> apartments;


    public Block(String name, Condominium condominium) {
        this.name = name;
        this.condominium = condominium;
    }
}
