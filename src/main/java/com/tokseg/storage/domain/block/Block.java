package com.tokseg.storage.domain.block;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;


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
    private UUID condominium_id;
    private String name;

    public Block(String name, UUID condominium_id) {
        this.condominium_id = condominium_id;
        this.name = name;
    }
}
