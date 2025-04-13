package tn.gestion.retour.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.gestion.retour.models.NonConformite;
import tn.gestion.retour.repository.NonConformiteRepository;

@Service
public class NonConformiteService {

    private final NonConformiteRepository nonConformiteRepository;

    public NonConformiteService(NonConformiteRepository nonConformiteRepository) {
        this.nonConformiteRepository = nonConformiteRepository;
    }

    // Enregistrer ou modifier une non-conformité
    public NonConformite saveNonConformite(NonConformite nonConformite) {
        return nonConformiteRepository.save(nonConformite);
    }

    // Supprimer une non-conformité par ID
    public void deleteNonConformite(Long id) {
        if (!nonConformiteRepository.existsById(id)) {
            throw new IllegalArgumentException("NonConformité avec ID " + id + " n'existe pas !");
        }
        nonConformiteRepository.deleteById(id);
    }

    // Récupérer toutes les non-conformités
    public List<NonConformite> getAllNonConformites() {
        return nonConformiteRepository.findAll();
    }

    // Récupérer une non-conformité par ID
    public NonConformite getNonConformiteById(Long id) {
        Optional<NonConformite> nc = nonConformiteRepository.findById(id);
        return nc.orElseThrow(() -> 
            new IllegalArgumentException("NonConformité avec ID " + id + " non trouvée.")
        );
    }

    // Récupérer les non-conformités par gravité
    public List<NonConformite> getNonConformitesByGravite(String gravite) {
        return nonConformiteRepository.findByGravite(gravite);
    }

    // Récupérer les non-conformités d'un produit spécifique
    public List<NonConformite> getNonConformitesByProduitId(Long produitId) {
        return nonConformiteRepository.findByProduitId(produitId);
    }

    // Récupérer les non-conformités liées à des retours
    public List<NonConformite> getLinkedToRetours() {
        return nonConformiteRepository.findLinkedToRetours();
    }

    // Récupérer les non-conformités non liées à des retours
    public List<NonConformite> getNotLinkedToRetours() {
        return nonConformiteRepository.findNotLinkedToRetours();
    }
}
