package com.tokseg.storage.domain.condominium;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tokseg.storage.domain.cabinet.Cabinet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.tokseg.storage.domain.block.Block;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.UUID;


@Table(name = "condominium")
@Entity(name = "condominium")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Condominium {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 20)
    private String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Block> blocks;

    @JsonIgnore
    @OneToMany(mappedBy = "condominium", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cabinet> cabinets;

    public Condominium(String name, String address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }
}
