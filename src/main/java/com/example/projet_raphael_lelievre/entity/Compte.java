package com.example.projet_raphael_lelievre.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "comptes")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_compte", nullable = false, unique = true, length = 20)
    private String numeroCompte;

    @Column(name = "solde", nullable = false)
    private Double solde;

    @Column(name = "date_ouverture", nullable = false)
    private LocalDate dateOuverture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}