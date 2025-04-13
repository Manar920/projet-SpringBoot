package tn.gestion.retour.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.gestion.retour.models.Produit;
import tn.gestion.retour.services.ProduitService;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // Récupérer tous les produits
    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    // Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        try {
            Produit produit = produitService.getProduitById(id);
            return ResponseEntity.ok(produit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer un ou plusieurs produits par référence
    @GetMapping("/reference/{reference}")
    public ResponseEntity<List<Produit>> getProduitsByReference(@PathVariable String reference) {
        List<Produit> produits = produitService.getProduitsByReference(reference);
        return ResponseEntity.ok(produits);
    }

    // Récupérer les produits avec stock faible
    @GetMapping("/stock/faible")
    public ResponseEntity<List<Produit>> getLowStockProducts(@RequestParam int seuil) {
        List<Produit> produits = produitService.getLowStockProducts(seuil);
        return ResponseEntity.ok(produits);
    }

    // Mettre à jour le stock d'un produit
    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestParam int quantite) {
        try {
            produitService.updateStock(id, quantite);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Produits classés par nombre de retours décroissant
    @GetMapping("/retours")
    public ResponseEntity<List<Object[]>> getProductsByReturnCount() {
        List<Object[]> produits = produitService.getProductsByReturnCount();
        return ResponseEntity.ok(produits);
    }

    // Produits ayant des non-conformités
    @GetMapping("/non-conformites")
    public ResponseEntity<List<Produit>> getProductsWithNonConformites() {
        List<Produit> produits = produitService.getProductsWithNonConformites();
        return ResponseEntity.ok(produits);
    }

    // Ajouter ou modifier un produit
    @PostMapping
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit produit) {
        Produit savedProduit = produitService.saveProduit(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
    }

    // Supprimer un produit par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        try {
            produitService.deleteProduit(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
