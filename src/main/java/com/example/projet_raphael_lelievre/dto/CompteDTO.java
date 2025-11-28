package com.example.projet_raphael_lelievre.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteDTO {

    private Long id;
    private String numeroCompte;
    private Double solde;
    private String type; // CompteCourant ou CompteEpargne en fonction de la classe
    private Double decouvertAutorise; // null si de classe épargne
    private Double tauxRemuneration;  // null si de classe courant
    private Long clientId;
}