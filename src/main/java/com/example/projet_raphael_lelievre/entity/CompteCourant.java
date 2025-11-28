package com.example.projet_raphael_lelievre.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "compte_courant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompteCourant extends Compte {

    @Column(name = "decouvert_autorise", nullable = false)
    @Builder.Default
    private Double decouvertAutorise = 1000.0;
}