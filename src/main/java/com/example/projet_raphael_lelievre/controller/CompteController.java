package com.example.projet_raphael_lelievre.controller;

import com.example.projet_raphael_lelievre.dto.CompteDTO;
import com.example.projet_raphael_lelievre.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @GetMapping("/{id}")
    public CompteDTO getCompte(@PathVariable Long id) {
        return compteService.getById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<CompteDTO> getComptesByClient(@PathVariable Long clientId) {
        return compteService.getByClient(clientId);
    }

    @PostMapping("/{id}/credit")
    public CompteDTO credit(@PathVariable Long id, @RequestParam Double montant) {
        return compteService.credit(id, montant);
    }

    @PostMapping("/{id}/debit")
    public CompteDTO debit(@PathVariable Long id, @RequestParam Double montant) {
        return compteService.debit(id, montant);
    }
}