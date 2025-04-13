package tn.gestion.retour.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.gestion.retour.models.Produit;
import tn.gestion.retour.repository.ProduitRepository;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // Enregistrer ou modifier un produit
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Supprimer un produit par ID
    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new IllegalArgumentException("Produit avec ID " + id + " n'existe pas !");
        }
        produitRepository.deleteById(id);
    }

    // Récupérer tous les produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Récupérer un produit par ID
    public Produit getProduitById(Long id) {
        Optional<Produit> produit = produitRepository.findById(id);
        return produit.orElseThrow(() ->
            new IllegalArgumentException("Produit avec ID " + id + " non trouvé.")
        );
    }

    // Récupérer un ou plusieurs produits par référence
    public List<Produit> getProduitsByReference(String reference) {
        return produitRepository.findByReference(reference);
    }

    // Récupérer les produits avec stock faible
    public List<Produit> getLowStockProducts(int seuil) {
        return produitRepository.findLowStockProducts(seuil);
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
    public List<Produit> getProductsWithNonConformites() {
        return produitRepository.findProductsWithNonConformites();
    }
}
