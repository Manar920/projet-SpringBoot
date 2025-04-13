package tn.gestion.retour.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.gestion.retour.models.HistoriqueRetour;
import tn.gestion.retour.repository.HistoriqueRetourRepository;

@Service
public class HistoriqueRetourService {

    private final HistoriqueRetourRepository historiqueRetourRepository;

    public HistoriqueRetourService(HistoriqueRetourRepository historiqueRetourRepository) {
        this.historiqueRetourRepository = historiqueRetourRepository;
    }

    // Ajouter ou mettre à jour un historique
    public HistoriqueRetour saveHistorique(HistoriqueRetour historiqueRetour) {
        return historiqueRetourRepository.save(historiqueRetour);
    }

    // Supprimer un historique par ID
    public void deleteHistorique(Long id) {
        if (!historiqueRetourRepository.existsById(id)) {
            throw new IllegalArgumentException("Historique avec ID " + id + " n'existe pas !");
        }
        historiqueRetourRepository.deleteById(id);
    }

    // Récupérer tous les historiques
    public List<HistoriqueRetour> getAllHistoriques() {
        return historiqueRetourRepository.findAll();
    }

    // Récupérer un historique par ID
    public HistoriqueRetour getHistoriqueById(Long id) {
        Optional<HistoriqueRetour> historique = historiqueRetourRepository.findById(id);
        return historique.orElseThrow(() -> 
            new IllegalArgumentException("Historique avec ID " + id + " non trouvé.")
        );
    }

    // Récupérer l'historique d'un retour spécifique
    public List<HistoriqueRetour> getHistoriquesByRetourId(Long retourId) {
        return historiqueRetourRepository.findByRetourId(retourId);
    }

    // Récupérer l'historique d'un employé spécifique
    public List<HistoriqueRetour> getHistoriquesByEmployeId(Long employeId) {
        return historiqueRetourRepository.findByEmployeId(employeId);
    }

    // Récupérer l'historique par type d'action
    public List<HistoriqueRetour> getHistoriquesByAction(String action) {
        return historiqueRetourRepository.findByAction(action);
    }

    // Récupérer les historiques entre deux dates
    public List<HistoriqueRetour> getHistoriquesBetweenDates(Date startDate, Date endDate) {
        return historiqueRetourRepository.findByDateBetween(startDate, endDate);
    }

    // Récupérer les 10 dernières actions
    public List<HistoriqueRetour> getRecentActions() {
        return historiqueRetourRepository.findRecentActions();
    }
}
