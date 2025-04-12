package tn.gestion.retour.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.gestion.retour.models.HistoriqueRetour;

public interface HistoriqueRetourRepository extends JpaRepository<HistoriqueRetour, Long> {
    
    // Trouver l'historique d'un retour spécifique
    List<HistoriqueRetour> findByRetourId(Long retourId);
    
    // Trouver les actions d'un employé spécifique
    List<HistoriqueRetour> findByEmployeId(Long employeId);
    
    // Trouver les actions par type
    List<HistoriqueRetour> findByAction(String action);
    
    // Trouver l'historique entre deux dates
    @Query("SELECT h FROM HistoriqueRetour h WHERE h.date BETWEEN :startDate AND :endDate")
    List<HistoriqueRetour> findByDateBetween(@Param("startDate") Date startDate, 
                                           @Param("endDate") Date endDate);
    
    // Dernières actions (pour dashboard)
    @Query("SELECT h FROM HistoriqueRetour h ORDER BY h.date DESC LIMIT 10")
    List<HistoriqueRetour> findRecentActions();
}
