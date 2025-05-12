package com.tokseg.storage.domain.block;

import com.tokseg.storage.domain.condominium.Condominium;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity(name = "block")
@Table(name = "block")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public Block(String name, Condominium condominium) {
        this.name = name;
        this.condominium = condominium;
    }
}
