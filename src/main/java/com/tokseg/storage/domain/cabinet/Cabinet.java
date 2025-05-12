package com.tokseg.storage.domain.cabinet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokseg.storage.domain.condominium.Condominium;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Table(name = "cabinet")
@Entity(name = "cabinet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({"condominium"})
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "condominium_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Condominium condominium;

    @JsonProperty("condominiumId")
    public UUID getCondominiumId() {
        return condominium != null ? condominium.getId() : null;
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean status;

    public Cabinet(Condominium condominium,String name, String location,boolean status) {
       this.condominium = condominium;
       this.name = name;
       this.location = location;
       this.status = status;
    }

}
