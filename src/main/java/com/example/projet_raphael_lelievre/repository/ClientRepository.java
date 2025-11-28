package com.example.projet_raphael_lelievre.repository;

import com.example.projet_raphael_lelievre.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}