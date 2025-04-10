package com.tokseg.storage.domain.block;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;


@Table(name = "block")
@Entity(name = "block")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "condominium_id")
    private UUID condominiumId;
    private String name;

    public Block(String name, UUID condominiumId) {
        this.condominiumId = condominiumId;
        this.name = name;
    }
}
