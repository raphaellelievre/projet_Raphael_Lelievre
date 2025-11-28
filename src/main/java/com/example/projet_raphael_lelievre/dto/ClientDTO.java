package com.example.projet_raphael_lelievre.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {

    private Long id;
    private String prenom;
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private Long conseillerId;
}