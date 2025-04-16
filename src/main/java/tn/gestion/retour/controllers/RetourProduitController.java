package tn.gestion.retour.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.gestion.retour.dto.RetourProduitDTO;
import tn.gestion.retour.mappers.RetourProduitMapper;
import tn.gestion.retour.models.RetourProduit;
import tn.gestion.retour.services.RetourProduitService;

@RestController
@RequestMapping("/api/retours")
public class RetourProduitController {

    @Autowired
    private RetourProduitService retourProduitService;

    // Récupérer tous les retours
    @GetMapping
    public ResponseEntity<List<RetourProduitDTO>> getAllRetours() {
        List<RetourProduitDTO> retours = retourProduitService.getAllRetours()
                                                          .stream()
                                                          .map(RetourProduitMapper::toDTO)
                                                          .toList();
        return ResponseEntity.ok(retours);
    }

    // Récupérer un retour par ID
    @GetMapping("/{id}")
    public ResponseEntity<RetourProduitDTO> getRetourById(@PathVariable Long id) {
        try {
            RetourProduitDTO retour = RetourProduitMapper.toDTO(retourProduitService.getRetourById(id));
            return ResponseEntity.ok(retour);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer les retours par état de traitement
    @GetMapping("/etat/{etatTraitement}")
    public ResponseEntity<List<RetourProduitDTO>> getRetoursByEtat(@PathVariable String etatTraitement) {
        List<RetourProduitDTO> retours = retourProduitService.getRetoursByEtat(etatTraitement)
                                                          .stream()
                                                          .map(RetourProduitMapper::toDTO)
                                                          .toList();
        return ResponseEntity.ok(retours);
    }

    // Récupérer les retours d’un client spécifique
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RetourProduitDTO>> getRetoursByClientId(@PathVariable Long clientId) {
        List<RetourProduitDTO> retours = retourProduitService.getRetoursByClientId(clientId)
                                                          .stream()
                                                          .map(RetourProduitMapper::toDTO)
                                                          .toList();
        return ResponseEntity.ok(retours);
    }

    // Récupérer les retours ayant une non-conformité
    @GetMapping("/non-conformite")
    public ResponseEntity<List<RetourProduitDTO>> getRetoursWithNonConformite() {
        List<RetourProduitDTO> retours = retourProduitService.getRetoursWithNonConformite()
                                                          .stream()
                                                          .map(RetourProduitMapper::toDTO)
                                                          .toList();
        return ResponseEntity.ok(retours);
    }

    // Récupérer les retours entre deux dates
    @GetMapping("/dates")
    public ResponseEntity<List<RetourProduitDTO>> getRetoursBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<RetourProduitDTO> retours = retourProduitService.getRetoursBetweenDates(startDate, endDate)
                                                          .stream()
                                                          .map(RetourProduitMapper::toDTO)
                                                          .toList();
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
    public ResponseEntity<RetourProduitDTO> saveRetourProduit(@RequestBody RetourProduitDTO retourProduitDTO) {
        RetourProduit savedRetour = retourProduitService.saveRetourProduit(RetourProduitMapper.toEntity(retourProduitDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(RetourProduitMapper.toDTO(savedRetour));
    }

    // Modifier l'état d’un retour
    @PutMapping("/{id}/etat")
    public ResponseEntity<RetourProduitDTO> updateEtatRetour(@PathVariable Long id, @RequestParam String nouvelEtat) {
        try {
            RetourProduit updatedRetour = retourProduitService.updateEtatRetour(id, nouvelEtat);
            return ResponseEntity.ok(RetourProduitMapper.toDTO(updatedRetour));
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
