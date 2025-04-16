package tn.gestion.retour.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.gestion.retour.dto.ProduitDTO;
import tn.gestion.retour.services.ProduitService;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Récupérer tous les produits
    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAllProduits() {
        List<ProduitDTO> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    //  Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id) {
        try {
            ProduitDTO produit = produitService.getProduitById(id);
            return ResponseEntity.ok(produit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //  Récupérer un ou plusieurs produits par référence
    @GetMapping("/reference/{reference}")
    public ResponseEntity<List<ProduitDTO>> getProduitsByReference(@PathVariable String reference) {
        List<ProduitDTO> produits = produitService.getProduitsByReference(reference);
        return ResponseEntity.ok(produits);
    }

    //  Récupérer les produits avec stock faible
    @GetMapping("/stock/faible")
    public ResponseEntity<List<ProduitDTO>> getLowStockProducts(@RequestParam int seuil) {
        List<ProduitDTO> produits = produitService.getLowStockProducts(seuil);
        return ResponseEntity.ok(produits);
    }

    //  Mettre à jour le stock d'un produit
    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestParam int quantite) {
        try {
            produitService.updateStock(id, quantite);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //  Produits classés par nombre de retours décroissant
    @GetMapping("/retours")
    public ResponseEntity<List<Object[]>> getProductsByReturnCount() {
        List<Object[]> produits = produitService.getProductsByReturnCount();
        return ResponseEntity.ok(produits);
    }

    //  Produits ayant des non-conformités
    @GetMapping("/non-conformites")
    public ResponseEntity<List<ProduitDTO>> getProductsWithNonConformites() {
        List<ProduitDTO> produits = produitService.getProductsWithNonConformites();
        return ResponseEntity.ok(produits);
    }

    //  Ajouter ou modifier un produit
    @PostMapping
    public ResponseEntity<ProduitDTO> saveProduit(@RequestBody ProduitDTO produitDTO) {
        ProduitDTO savedProduit = produitService.saveProduit(produitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
    }

    //  Supprimer un produit par ID
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
