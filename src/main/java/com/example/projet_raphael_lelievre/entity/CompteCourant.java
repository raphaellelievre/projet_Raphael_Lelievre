package com.example.projet_raphael_lelievre.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "compte_courant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteCourant extends Compte {

    @Column(name = "decouvert_autorise", nullable = false)
    @Builder.Default
    private Double decouvertAutorise = 1000.0;
}