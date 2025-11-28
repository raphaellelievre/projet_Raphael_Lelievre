package com.example.projet_raphael_lelievre.repository;

import com.example.projet_raphael_lelievre.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}