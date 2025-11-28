package com.example.projet_raphael_lelievre.service;

import com.example.projet_raphael_lelievre.entity.CarteBancaire;
import com.example.projet_raphael_lelievre.entity.Compte;
import com.example.projet_raphael_lelievre.repository.CarteBancaireRepository;
import com.example.projet_raphael_lelievre.repository.CompteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarteBancaireService {

    private final CarteBancaireRepository carteRepo;
    private final CompteRepository compteRepo;

    public CarteBancaire createCard(Long idCompte, CarteBancaire.TypeCarte type) {
        Compte compte = compteRepo.findById(idCompte)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé."));

        CarteBancaire carte = CarteBancaire.builder()
            .type(type).compte(compte).active(true).build();

        log.info("Création d'une carte {} pour le compte {}", type, idCompte);
        return carteRepo.save(carte);
    }

    public List<CarteBancaire> getCardsByClient(Long idClient) {
        return carteRepo.findByCompte_Client_Id(idClient);
    }

    public void disableCardsForClient(Long idClient) {
        List<CarteBancaire> cards = getCardsByClient(idClient);

        cards.forEach(c -> c.setActive(false));

        carteRepo.saveAll(cards);

        log.info("Toutes les cartes du client {} ont été désactivées.", idClient);
    }
}