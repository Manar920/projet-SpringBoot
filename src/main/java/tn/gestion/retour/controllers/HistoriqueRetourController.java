package tn.gestion.retour.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.gestion.retour.models.HistoriqueRetour;
import tn.gestion.retour.services.HistoriqueRetourService;

@RestController
@RequestMapping("/api/historiques-retours")
public class HistoriqueRetourController {

    private final HistoriqueRetourService historiqueRetourService;

    public HistoriqueRetourController(HistoriqueRetourService historiqueRetourService) {
        this.historiqueRetourService = historiqueRetourService;
    }

    // Récupérer tous les historiques
    @GetMapping
    public ResponseEntity<List<HistoriqueRetour>> getAllHistoriques() {
        List<HistoriqueRetour> historiques = historiqueRetourService.getAllHistoriques();
        return ResponseEntity.ok(historiques);
    }

    // Récupérer un historique par ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueRetour> getHistoriqueById(@PathVariable Long id) {
        try {
            HistoriqueRetour historique = historiqueRetourService.getHistoriqueById(id);
            return ResponseEntity.ok(historique);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer l'historique d'un retour spécifique
    @GetMapping("/retour/{retourId}")
    public ResponseEntity<List<HistoriqueRetour>> getHistoriquesByRetourId(@PathVariable Long retourId) {
        List<HistoriqueRetour> historiques = historiqueRetourService.getHistoriquesByRetourId(retourId);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer l'historique d'un employé spécifique
    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<HistoriqueRetour>> getHistoriquesByEmployeId(@PathVariable Long employeId) {
        List<HistoriqueRetour> historiques = historiqueRetourService.getHistoriquesByEmployeId(employeId);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer l'historique par type d'action
    @GetMapping("/action/{action}")
    public ResponseEntity<List<HistoriqueRetour>> getHistoriquesByAction(@PathVariable String action) {
        List<HistoriqueRetour> historiques = historiqueRetourService.getHistoriquesByAction(action);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer les historiques entre deux dates
    @GetMapping("/dates")
    public ResponseEntity<List<HistoriqueRetour>> getHistoriquesBetweenDates(
            @RequestParam Date startDate, @RequestParam Date endDate) {
        List<HistoriqueRetour> historiques = historiqueRetourService.getHistoriquesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer les 10 dernières actions
    @GetMapping("/recent-actions")
    public ResponseEntity<List<HistoriqueRetour>> getRecentActions() {
        List<HistoriqueRetour> recentActions = historiqueRetourService.getRecentActions();
        return ResponseEntity.ok(recentActions);
    }

    // Ajouter ou mettre à jour un historique
    @PostMapping
    public ResponseEntity<HistoriqueRetour> saveHistorique(@RequestBody HistoriqueRetour historiqueRetour) {
        HistoriqueRetour savedHistorique = historiqueRetourService.saveHistorique(historiqueRetour);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHistorique);
    }

    // Supprimer un historique par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorique(@PathVariable Long id) {
        try {
            historiqueRetourService.deleteHistorique(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
