package tn.gestion.retour.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.gestion.retour.dto.NonConformiteDTO;
import tn.gestion.retour.mappers.NonConformiteMapper;
import tn.gestion.retour.models.NonConformite;
import tn.gestion.retour.repository.NonConformiteRepository;

@Service
public class NonConformiteService {

    @Autowired
    private NonConformiteRepository nonConformiteRepository;

    // Enregistrer ou modifier une non-conformité
    public NonConformiteDTO saveNonConformite(NonConformiteDTO nonConformiteDTO) {
        NonConformite nonConformite = NonConformiteMapper.toEntity(nonConformiteDTO);
        NonConformite savedNonConformite = nonConformiteRepository.save(nonConformite);
        return NonConformiteMapper.toDTO(savedNonConformite);
    }

    // Supprimer une non-conformité par ID
    public void deleteNonConformite(Long id) {
        if (!nonConformiteRepository.existsById(id)) {
            throw new IllegalArgumentException("NonConformité avec ID " + id + " n'existe pas !");
        }
        nonConformiteRepository.deleteById(id);
    }

    // Récupérer toutes les non-conformités
    public List<NonConformiteDTO> getAllNonConformites() {
        List<NonConformite> nonConformites = nonConformiteRepository.findAll();
        return nonConformites.stream()
                .map(NonConformiteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer une non-conformité par ID
    public NonConformiteDTO getNonConformiteById(Long id) {
        Optional<NonConformite> nc = nonConformiteRepository.findById(id);
        return nc.map(NonConformiteMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("NonConformité avec ID " + id + " non trouvée."));
    }

    // Récupérer les non-conformités par gravité
    public List<NonConformiteDTO> getNonConformitesByGravite(String gravite) {
        List<NonConformite> nonConformites = nonConformiteRepository.findByGravite(gravite);
        return nonConformites.stream()
                .map(NonConformiteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les non-conformités d'un produit spécifique
    public List<NonConformiteDTO> getNonConformitesByProduitId(Long produitId) {
        List<NonConformite> nonConformites = nonConformiteRepository.findByProduitId(produitId);
        return nonConformites.stream()
                .map(NonConformiteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les non-conformités liées à des retours
    public List<NonConformiteDTO> getLinkedToRetours() {
        List<NonConformite> nonConformites = nonConformiteRepository.findLinkedToRetours();
        return nonConformites.stream()
                .map(NonConformiteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les non-conformités non liées à des retours
    public List<NonConformiteDTO> getNotLinkedToRetours() {
        List<NonConformite> nonConformites = nonConformiteRepository.findNotLinkedToRetours();
        return nonConformites.stream()
                .map(NonConformiteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
