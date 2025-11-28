package com.example.projet_raphael_lelievre.service;

import com.example.projet_raphael_lelievre.dto.ClientDTO;
import com.example.projet_raphael_lelievre.dto.CompteDTO;
import com.example.projet_raphael_lelievre.dto.RapportClientDTO;
import com.example.projet_raphael_lelievre.entity.Client;
import com.example.projet_raphael_lelievre.entity.Compte;
import com.example.projet_raphael_lelievre.entity.Conseiller;
import com.example.projet_raphael_lelievre.repository.ClientRepository;
import com.example.projet_raphael_lelievre.repository.ConseillerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ConseillerRepository conseillerRepository;
    private final CompteService compteService;

    public ClientDTO create(ClientDTO dto) {
        Conseiller conseiller = conseillerRepository.findById(dto.getConseillerId())
            .orElseThrow(() -> new RuntimeException("Conseiller introuvable"));

        Client client = Client.builder()
            .prenom(dto.getPrenom())
            .nom(dto.getNom())
            .adresse(dto.getAdresse())
            .codePostal(dto.getCodePostal())
            .ville(dto.getVille())
            .telephone(dto.getTelephone())
            .conseiller(conseiller)
            .build();

        clientRepository.save(client);
        dto.setId(client.getId());
        return dto;
    }

    public ClientDTO getById(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client introuvable"));
        return toDTO(client);
    }

    public List<ClientDTO> getAll() {
        return clientRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client introuvable"));

        client.setPrenom(dto.getPrenom());
        client.setNom(dto.getNom());
        client.setAdresse(dto.getAdresse());
        client.setCodePostal(dto.getCodePostal());
        client.setVille(dto.getVille());
        client.setTelephone(dto.getTelephone());

        clientRepository.save(client);
        return toDTO(client);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        clientRepository.delete(client);
    }

    private ClientDTO toDTO(Client c) {
        return ClientDTO.builder()
            .id(c.getId())
            .prenom(c.getPrenom())
            .nom(c.getNom())
            .adresse(c.getAdresse())
            .codePostal(c.getCodePostal())
            .ville(c.getVille())
            .telephone(c.getTelephone())
            .conseillerId(c.getConseiller().getId())
            .build();
    }

    public RapportClientDTO genererRapport(Long clientId) {
        Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Client introuvable"));

        List<CompteDTO> comptesDTO = client.getComptes()
            .stream().map(c -> compteService.toDTO(c)).toList();

        double total = client.getComptes().stream().mapToDouble(Compte::getSolde).sum();

        return RapportClientDTO.builder()
            .clientId(client.getId())
            .nom(client.getNom())
            .prenom(client.getPrenom())
            .comptes(comptesDTO)
            .totalSolde(total)
            .build();
    }
}