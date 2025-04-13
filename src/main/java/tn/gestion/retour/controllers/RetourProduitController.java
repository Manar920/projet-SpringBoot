package tn.gestion.retour.controllers;

import java.sql.Date;
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

import tn.gestion.retour.models.RetourProduit;
import tn.gestion.retour.services.RetourProduitService;

@RestController
@RequestMapping("/api/retours")
public class RetourProduitController {

    private final RetourProduitService retourProduitService;

    public RetourProduitController(RetourProduitService retourProduitService) {
        this.retourProduitService = retourProduitService;
    }

    // Récupérer tous les retours
    @GetMapping
    public ResponseEntity<List<RetourProduit>> getAllRetours() {
        List<RetourProduit> retours = retourProduitService.getAllRetours();
        return ResponseEntity.ok(retours);
    }

    // Récupérer un retour par ID
    @GetMapping("/{id}")
    public ResponseEntity<RetourProduit> getRetourById(@PathVariable Long id) {
        try {
            RetourProduit retour = retourProduitService.getRetourById(id);
            return ResponseEntity.ok(retour);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer les retours par état de traitement
    @GetMapping("/etat/{etatTraitement}")
    public ResponseEntity<List<RetourProduit>> getRetoursByEtat(@PathVariable String etatTraitement) {
        List<RetourProduit> retours = retourProduitService.getRetoursByEtat(etatTraitement);
        return ResponseEntity.ok(retours);
    }

    // Récupérer les retours d’un client spécifique
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RetourProduit>> getRetoursByClientId(@PathVariable Long clientId) {
        List<RetourProduit> retours = retourProduitService.getRetoursByClientId(clientId);
        return ResponseEntity.ok(retours);
    }

    // Récupérer les retours ayant une non-conformité
    @GetMapping("/non-conformite")
    public ResponseEntity<List<RetourProduit>> getRetoursWithNonConformite() {
        List<RetourProduit> retours = retourProduitService.getRetoursWithNonConformite();
        return ResponseEntity.ok(retours);
    }

    // Récupérer les retours entre deux dates
    @GetMapping("/dates")
    public ResponseEntity<List<RetourProduit>> getRetoursBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<RetourProduit> retours = retourProduitService.getRetoursBetweenDates(startDate, endDate);
        return ResponseEntity.ok(retours);
    }

    // Statistiques : nombre de retours par état
    @GetMapping("/stats/etat")
    public ResponseEntity<List<Object[]>> countRetoursByEtat() {
        List<Object[]> stats = retourProduitService.countRetoursByEtat();
        return ResponseEntity.ok(stats);
    }

    // Ajouter ou modifier un retour
    @PostMapping
    public ResponseEntity<RetourProduit> saveRetourProduit(@RequestBody RetourProduit retourProduit) {
        RetourProduit savedRetour = retourProduitService.saveRetourProduit(retourProduit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRetour);
    }

    // Modifier l'état d’un retour
    @PutMapping("/{id}/etat")
    public ResponseEntity<RetourProduit> updateEtatRetour(@PathVariable Long id, @RequestParam String nouvelEtat) {
        try {
            RetourProduit updatedRetour = retourProduitService.updateEtatRetour(id, nouvelEtat);
            return ResponseEntity.ok(updatedRetour);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un retour par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetourProduit(@PathVariable Long id) {
        try {
            retourProduitService.deleteRetourProduit(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}