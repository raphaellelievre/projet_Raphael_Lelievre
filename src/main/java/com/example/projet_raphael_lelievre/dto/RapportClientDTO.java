package com.example.projet_raphael_lelievre.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RapportClientDTO {
    private Long clientId;
    private String nom;
    private String prenom;
    private List<CompteDTO> comptes;
    private Double totalSolde;
}