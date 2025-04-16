package tn.gestion.retour.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.gestion.retour.dto.RetourProduitDTO;
import tn.gestion.retour.mappers.RetourProduitMapper;
import tn.gestion.retour.models.RetourProduit;
import tn.gestion.retour.repository.RetourProduitRepository;

@Service
public class RetourProduitService {
    @Autowired
    private RetourProduitRepository retourProduitRepository;

    public RetourProduit saveRetourProduit(RetourProduit retourProduit) {
        return retourProduitRepository.save(retourProduit);
    }

    // Supprimer un retour par ID
    public void deleteRetourProduit(Long id) {
        if (!retourProduitRepository.existsById(id)) {
            throw new IllegalArgumentException("Retour avec ID " + id + " non trouvé.");
        }
        retourProduitRepository.deleteById(id);
    }

    // Récupérer tous les retours
    public List<RetourProduit> getAllRetours() {
        return retourProduitRepository.findAll();
    }

    // Trouver un retour par ID
    public RetourProduit getRetourById(Long id) {
        return retourProduitRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException("Retour avec ID " + id + " non trouvé.")
        );
    }

    // Récupérer les retours par état de traitement
    public List<RetourProduit> getRetoursByEtat(String etatTraitement) {
        return retourProduitRepository.findByEtatTraitement(etatTraitement);
    }

    // Récupérer les retours d’un client
    public List<RetourProduit> getRetoursByClientId(Long clientId) {
        return retourProduitRepository.findByClientId(clientId);
    }

    // Récupérer les retours liés à une non-conformité
    public List<RetourProduit> getRetoursWithNonConformite() {
        return retourProduitRepository.findWithNonConformite();
    }

    // Récupérer les retours dans une plage de dates
    public List<RetourProduit> getRetoursBetweenDates(Date startDate, Date endDate) {
        return retourProduitRepository.findByDateBetween(startDate, endDate);
    }

    // Statistiques : nombre de retours par état
    public List<Object[]> countRetoursByEtat() {
        return retourProduitRepository.countByEtatTraitement();
    }

    // Modifier l'état d’un retour (ex: EN_ATTENTE → VALIDE)
    @Transactional
    public RetourProduit updateEtatRetour(Long id, String nouvelEtat) {
        RetourProduit retour = retourProduitRepository.findById(id).orElseThrow(() -> 
            new IllegalArgumentException("Retour avec ID " + id + " non trouvé.")
        );
        retour.setEtatTraitement(nouvelEtat);
        return retourProduitRepository.save(retour);
    }
}
