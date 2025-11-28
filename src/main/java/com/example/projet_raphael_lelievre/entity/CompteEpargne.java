package com.example.projet_raphael_lelievre.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compte_epargne")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteEpargne extends Compte {

    @Column(name = "taux_remuneration", nullable = false)
    @Builder.Default
    private Double tauxRemuneration = 3.0;
}