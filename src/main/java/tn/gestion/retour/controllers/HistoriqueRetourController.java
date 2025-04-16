package tn.gestion.retour.controllers;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.gestion.retour.dto.HistoriqueRetourDTO;
import tn.gestion.retour.services.HistoriqueRetourService;

@RestController
@RequestMapping("/api/historiques-retours")
public class HistoriqueRetourController {

    @Autowired
    private HistoriqueRetourService historiqueRetourService;

    // Récupérer tous les historiques
    @GetMapping
    public ResponseEntity<List<HistoriqueRetourDTO>> getAllHistoriques() {
        List<HistoriqueRetourDTO> historiques = historiqueRetourService.getAllHistoriques();
        return ResponseEntity.ok(historiques);
    }

    // Récupérer un historique par ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueRetourDTO> getHistoriqueById(@PathVariable Long id) {
        try {
            HistoriqueRetourDTO historique = historiqueRetourService.getHistoriqueById(id);
            return ResponseEntity.ok(historique);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer l'historique d'un retour spécifique
    @GetMapping("/retour/{retourId}")
    public ResponseEntity<List<HistoriqueRetourDTO>> getHistoriquesByRetourId(@PathVariable Long retourId) {
        List<HistoriqueRetourDTO> historiques = historiqueRetourService.getHistoriquesByRetourId(retourId);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer l'historique d'un employé spécifique
    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<HistoriqueRetourDTO>> getHistoriquesByEmployeId(@PathVariable Long employeId) {
        List<HistoriqueRetourDTO> historiques = historiqueRetourService.getHistoriquesByEmployeId(employeId);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer l'historique par type d'action
    @GetMapping("/action/{action}")
    public ResponseEntity<List<HistoriqueRetourDTO>> getHistoriquesByAction(@PathVariable String action) {
        List<HistoriqueRetourDTO> historiques = historiqueRetourService.getHistoriquesByAction(action);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer les historiques entre deux dates
    @GetMapping("/dates")
    public ResponseEntity<List<HistoriqueRetourDTO>> getHistoriquesBetweenDates(
            @RequestParam Date startDate, @RequestParam Date endDate) {
        List<HistoriqueRetourDTO> historiques = historiqueRetourService.getHistoriquesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(historiques);
    }

    // Récupérer les 10 dernières actions
    @GetMapping("/recent-actions")
    public ResponseEntity<List<HistoriqueRetourDTO>> getRecentActions() {
        List<HistoriqueRetourDTO> recentActions = historiqueRetourService.getRecentActions();
        return ResponseEntity.ok(recentActions);
    }

    // Ajouter ou mettre à jour un historique
    @PostMapping
    public ResponseEntity<HistoriqueRetourDTO> saveHistorique(@RequestBody HistoriqueRetourDTO historiqueRetourDTO) {
        HistoriqueRetourDTO savedHistorique = historiqueRetourService.saveHistorique(historiqueRetourDTO);
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
