package tn.gestion.retour.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.gestion.retour.dto.ProduitDTO;
import tn.gestion.retour.mappers.ProduitMapper;
import tn.gestion.retour.models.Produit;
import tn.gestion.retour.repository.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;

    // Enregistrer ou modifier un produit
    public ProduitDTO saveProduit(ProduitDTO produitDTO) {
        Produit produit = produitMapper.toEntity(produitDTO);
        Produit savedProduit = produitRepository.save(produit);
        return produitMapper.toDTO(savedProduit);
    }

    // Supprimer un produit par ID
    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new IllegalArgumentException("Produit avec ID " + id + " n'existe pas !");
        }
        produitRepository.deleteById(id);
    }

    // Récupérer tous les produits
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un produit par ID
    public ProduitDTO getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit avec ID " + id + " non trouvé."));
        return produitMapper.toDTO(produit);
    }

    // Récupérer un ou plusieurs produits par référence
    public List<ProduitDTO> getProduitsByReference(String reference) {
        return produitRepository.findByReference(reference)
                .stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les produits avec stock faible
    public List<ProduitDTO> getLowStockProducts(int seuil) {
        return produitRepository.findLowStockProducts(seuil)
                .stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Mettre à jour le stock d'un produit (+ / -)
    @Transactional
    public void updateStock(Long produitId, int quantite) {
        if (!produitRepository.existsById(produitId)) {
            throw new IllegalArgumentException("Produit avec ID " + produitId + " non trouvé pour mise à jour du stock.");
        }
        produitRepository.updateStock(produitId, quantite);
    }

    // Produits classés par nombre de retours décroissant
    public List<Object[]> getProductsByReturnCount() {
        return produitRepository.findProductsByReturnCount();
    }

    // Produits ayant des non-conformités
    public List<ProduitDTO> getProductsWithNonConformites() {
        return produitRepository.findProductsWithNonConformites()
                .stream()
                .map(produitMapper::toDTO)
                .collect(Collectors.toList());
    }
}
