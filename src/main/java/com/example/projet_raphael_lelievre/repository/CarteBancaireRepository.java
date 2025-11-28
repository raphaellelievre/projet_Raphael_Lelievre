package com.example.projet_raphael_lelievre.repository;

import com.example.projet_raphael_lelievre.entity.CarteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Long> {

    List<CarteBancaire> findByCompte_Client_Id(Long clientId);
}