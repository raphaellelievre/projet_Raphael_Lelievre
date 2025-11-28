package com.example.projet_raphael_lelievre.controller;

import com.example.projet_raphael_lelievre.entity.CarteBancaire;
import com.example.projet_raphael_lelievre.service.CarteBancaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartes")
@RequiredArgsConstructor
public class CarteBancaireController {

    private final CarteBancaireService service;

    @PostMapping("/create/{idCompte}")
    public CarteBancaire create(
            @PathVariable Long idCompte,
            @RequestParam CarteBancaire.TypeCarte type
    ) {
        return service.createCard(idCompte, type);
    }

    @GetMapping("/client/{idClient}")
    public List<CarteBancaire> getByClient(@PathVariable Long idClient) {
        return service.getCardsByClient(idClient);
    }
}