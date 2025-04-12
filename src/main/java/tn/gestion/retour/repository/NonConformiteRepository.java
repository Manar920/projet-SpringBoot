package tn.gestion.retour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.gestion.retour.models.NonConformite;

public interface NonConformiteRepository extends JpaRepository<NonConformite, Long> {
    
    // Trouver les non-conformités par gravité
    List<NonConformite> findByGravite(String gravite);
    
    // Trouver les non-conformités d'un produit spécifique
    List<NonConformite> findByProduitId(Long produitId);
    
    // Trouver les non-conformités associées à des retours
    @Query("SELECT nc FROM NonConformite nc WHERE nc.retour IS NOT NULL")
    List<NonConformite> findLinkedToRetours();
    
    // Trouver les non-conformités non associées à des retours
    @Query("SELECT nc FROM NonConformite nc WHERE nc.retour IS NULL")
    List<NonConformite> findNotLinkedToRetours();
}
