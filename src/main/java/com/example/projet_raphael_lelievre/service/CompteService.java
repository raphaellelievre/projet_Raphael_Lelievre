package com.example.projet_raphael_lelievre.service;

import com.example.projet_raphael_lelievre.dto.CompteDTO;
import com.example.projet_raphael_lelievre.entity.*;
import com.example.projet_raphael_lelievre.repository.ClientRepository;
import com.example.projet_raphael_lelievre.repository.CompteRepository;
import com.example.projet_raphael_lelievre.repository.CompteCourantRepository;
import com.example.projet_raphael_lelievre.repository.CompteEpargneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final CompteCourantRepository compteCourantRepository;
    private final CompteEpargneRepository compteEpargneRepository;
    private final ClientRepository clientRepository;

    public CompteDTO getById(Long id) {
        Compte compte = compteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        return toDTO(compte);
    }

    public List<CompteDTO> getByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client introuvable"));
        return client.getComptes().stream().map(this::toDTO).toList();
    }

    public CompteDTO credit(Long id, Double montant) {
        if (montant <= 0) throw new RuntimeException("Montant invalide");
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);
        return toDTO(compte);
    }

    public CompteDTO debit(Long id, Double montant) {
        if (montant <= 0) throw new RuntimeException("Montant invalide");

        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        if (compte instanceof CompteCourant courant) {
            if (compte.getSolde() - montant < -courant.getDecouvertAutorise()) {
                throw new RuntimeException("Découvert dépassé");
            }
        } else {
            if (compte.getSolde() - montant < 0) {
                throw new RuntimeException("Solde insuffisant");
            }
        }

        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);

        return toDTO(compte);
    }

    @Transactional
    public void virement(Long idSource, Long idDestination, Double montant) {
        if (idSource.equals(idDestination)) {
            throw new RuntimeException("Les comptes doivent être différents");
        }
        if (montant == null || montant <= 0) {
            throw new RuntimeException("Montant invalide");
        }
        debit(idSource, montant);
        credit(idDestination, montant);
    }

    public CompteDTO toDTO(Compte c) {
        if (c instanceof CompteCourant cc) {
            return CompteDTO.builder()
                .id(cc.getId())
                .numeroCompte(cc.getNumeroCompte())
                .solde(cc.getSolde())
                .type("CompteCourant")
                .decouvertAutorise(cc.getDecouvertAutorise())
                .clientId(cc.getClient().getId())
                .build();
        } else {
            CompteEpargne ce = (CompteEpargne) c;
            return CompteDTO.builder()
                .id(ce.getId())
                .numeroCompte(ce.getNumeroCompte())
                .solde(ce.getSolde())
                .type("CompteEpargne")
                .tauxRemuneration(ce.getTauxRemuneration())
                .clientId(ce.getClient().getId())
                 .build();
        }
    }
}