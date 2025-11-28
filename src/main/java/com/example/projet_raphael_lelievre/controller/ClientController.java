package com.example.projet_raphael_lelievre.controller;

import com.example.projet_raphael_lelievre.dto.ClientDTO;
import com.example.projet_raphael_lelievre.dto.RapportClientDTO;
import com.example.projet_raphael_lelievre.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO dto) {
        return clientService.create(dto);
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAll();
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO dto) {
        return clientService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

    @GetMapping("/{id}/rapport")
    public RapportClientDTO rapport(@PathVariable Long id) {
        return clientService.genererRapport(id);
    }
}