package com.example.projet_raphael_lelievre.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeCarte type;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "compte_id")
    private Compte compte;

    public enum TypeCarte {
        VISA_ELECTRON,
        VISA_PREMIER
    }
}