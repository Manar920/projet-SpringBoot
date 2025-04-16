package tn.gestion.retour.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.gestion.retour.dto.HistoriqueRetourDTO;
import tn.gestion.retour.mappers.HistoriqueRetourMapper;
import tn.gestion.retour.models.HistoriqueRetour;
import tn.gestion.retour.repository.HistoriqueRetourRepository;

@Service
public class HistoriqueRetourService {

    @Autowired
    private HistoriqueRetourRepository historiqueRetourRepository;

    @Autowired
    private HistoriqueRetourMapper historiqueRetourMapper;

    // Ajouter ou mettre à jour un historique
    public HistoriqueRetourDTO saveHistorique(HistoriqueRetourDTO dto) {
        HistoriqueRetour entity = historiqueRetourMapper.toEntity(dto);
        HistoriqueRetour saved = historiqueRetourRepository.save(entity);
        return historiqueRetourMapper.toDTO(saved);
    }

    // Supprimer un historique par ID
    public void deleteHistorique(Long id) {
        if (!historiqueRetourRepository.existsById(id)) {
            throw new IllegalArgumentException("Historique avec ID " + id + " n'existe pas !");
        }
        historiqueRetourRepository.deleteById(id);
    }

    // Récupérer tous les historiques
    public List<HistoriqueRetourDTO> getAllHistoriques() {
        return historiqueRetourRepository.findAll()
                .stream()
                .map(historiqueRetourMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer un historique par ID
    public HistoriqueRetourDTO getHistoriqueById(Long id) {
        Optional<HistoriqueRetour> historique = historiqueRetourRepository.findById(id);
        return historique.map(historiqueRetourMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Historique avec ID " + id + " non trouvé."));
    }

    // Récupérer l'historique d'un retour spécifique
    public List<HistoriqueRetourDTO> getHistoriquesByRetourId(Long retourId) {
        return historiqueRetourRepository.findByRetourId(retourId)
                .stream()
                .map(historiqueRetourMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer l'historique d'un employé spécifique
    public List<HistoriqueRetourDTO> getHistoriquesByEmployeId(Long employeId) {
        return historiqueRetourRepository.findByEmployeId(employeId)
                .stream()
                .map(historiqueRetourMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer l'historique par type d'action
    public List<HistoriqueRetourDTO> getHistoriquesByAction(String action) {
        return historiqueRetourRepository.findByAction(action)
                .stream()
                .map(historiqueRetourMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les historiques entre deux dates
    public List<HistoriqueRetourDTO> getHistoriquesBetweenDates(Date startDate, Date endDate) {
        return historiqueRetourRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(historiqueRetourMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Récupérer les 10 dernières actions
    public List<HistoriqueRetourDTO> getRecentActions() {
        return historiqueRetourRepository.findRecentActions()
                .stream()
                .map(historiqueRetourMapper::toDTO)
                .collect(Collectors.toList());
    }
}
