package com.tokseg.storage.domain.condominium;


import jakarta.persistence.*;
import lombok.Getter;

import lombok.Setter;

import java.util.UUID;


@Table(name = "condominium")
@Entity(name = "condominium")
@Getter
@Setter
public class Condominium {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String address;
    private String telephone;

    public Condominium(String name, String address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }
}
