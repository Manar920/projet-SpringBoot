package tn.gestion.retour.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.gestion.retour.models.RetourProduit;

public interface RetourProduitRepository extends JpaRepository<RetourProduit, Long> {
    
    // Trouver les retours par état de traitement
    List<RetourProduit> findByEtatTraitement(String etatTraitement);
    
    // Trouver les retours d'un client spécifique
    List<RetourProduit> findByClientId(Long clientId);
    
    // Trouver les retours avec non-conformité
    @Query("SELECT r FROM RetourProduit r WHERE r.nonConformite IS NOT NULL")
    List<RetourProduit> findWithNonConformite();
    
    // Trouver les retours entre deux dates
    @Query("SELECT r FROM RetourProduit r WHERE r.date BETWEEN :startDate AND :endDate")
    List<RetourProduit> findByDateBetween(@Param("startDate") Date startDate, 
                                         @Param("endDate") Date endDate);
    
    // Compter les retours par état
    @Query("SELECT r.etatTraitement, COUNT(r) FROM RetourProduit r GROUP BY r.etatTraitement")
    List<Object[]> countByEtatTraitement();
}
