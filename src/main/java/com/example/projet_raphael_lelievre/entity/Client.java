package com.example.projet_raphael_lelievre.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prenom", nullable = false, length = 255)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 255)
    private String nom;

    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "code_postal", length = 32)
    private String codePostal;

    @Column(name = "ville", length = 255)
    private String ville;

    @Column(name = "telephone", length = 32)
    private String telephone;

    // 1 conseiller par client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conseiller_id", nullable = false)
    private Conseiller conseiller;
}