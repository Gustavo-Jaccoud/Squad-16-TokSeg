package com.tokseg.storage.domain.cabinet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "cabinet")
@Entity(name = "cabinet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "condominium_id")
    private UUID condominiumId;
    private String name;
    private String location;
    private boolean status;

    public Cabinet(UUID condominiumId,String name, String location,boolean status) {
       this.condominiumId = condominiumId;
       this.name = name;
       this.location = location;
       this.status = status;
    }

}
