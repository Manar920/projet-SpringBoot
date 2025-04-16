package tn.gestion.retour.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.gestion.retour.dto.NonConformiteDTO;
import tn.gestion.retour.services.NonConformiteService;

@RestController
@RequestMapping("/api/non-conformites")
public class NonConformiteController {

    @Autowired
    private NonConformiteService nonConformiteService;

    // Récupérer toutes les non-conformités
    @GetMapping
    public ResponseEntity<List<NonConformiteDTO>> getAllNonConformites() {
        List<NonConformiteDTO> nonConformites = nonConformiteService.getAllNonConformites();
        return ResponseEntity.ok(nonConformites);
    }

    // Récupérer une non-conformité par ID
    @GetMapping("/{id}")
    public ResponseEntity<NonConformiteDTO> getNonConformiteById(@PathVariable Long id) {
        try {
            NonConformiteDTO nonConformite = nonConformiteService.getNonConformiteById(id);
            return ResponseEntity.ok(nonConformite);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer les non-conformités par gravité
    @GetMapping("/gravite/{gravite}")
    public ResponseEntity<List<NonConformiteDTO>> getNonConformitesByGravite(@PathVariable String gravite) {
        List<NonConformiteDTO> nonConformites = nonConformiteService.getNonConformitesByGravite(gravite);
        return ResponseEntity.ok(nonConformites);
    }

    // Récupérer les non-conformités d'un produit spécifique
    @GetMapping("/produit/{produitId}")
    public ResponseEntity<List<NonConformiteDTO>> getNonConformitesByProduitId(@PathVariable Long produitId) {
        List<NonConformiteDTO> nonConformites = nonConformiteService.getNonConformitesByProduitId(produitId);
        return ResponseEntity.ok(nonConformites);
    }

    // Récupérer les non-conformités liées à des retours
    @GetMapping("/liées-aux-retours")
    public ResponseEntity<List<NonConformiteDTO>> getLinkedToRetours() {
        List<NonConformiteDTO> nonConformites = nonConformiteService.getLinkedToRetours();
        return ResponseEntity.ok(nonConformites);
    }

    // Récupérer les non-conformités non liées à des retours
    @GetMapping("/non-liées-aux-retours")
    public ResponseEntity<List<NonConformiteDTO>> getNotLinkedToRetours() {
        List<NonConformiteDTO> nonConformites = nonConformiteService.getNotLinkedToRetours();
        return ResponseEntity.ok(nonConformites);
    }

    // Enregistrer ou modifier une non-conformité
    @PostMapping
    public ResponseEntity<NonConformiteDTO> saveNonConformite(@RequestBody NonConformiteDTO nonConformiteDTO) {
        NonConformiteDTO savedNonConformite = nonConformiteService.saveNonConformite(nonConformiteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNonConformite);
    }

    // Supprimer une non-conformité par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNonConformite(@PathVariable Long id) {
        try {
            nonConformiteService.deleteNonConformite(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
