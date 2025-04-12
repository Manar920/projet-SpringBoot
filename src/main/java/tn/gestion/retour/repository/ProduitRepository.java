package tn.gestion.retour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.gestion.retour.models.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
	
    // Trouver un produit par référence
    List<Produit> findByReference(String reference);
    // Trouver les produits avec stock faible
    @Query("SELECT p FROM Produit p WHERE p.quantiteStock < :seuil")
    List<Produit> findLowStockProducts(@Param("seuil") int seuil);
    
 // Mettre à jour le stock
    @Modifying
    @Query("UPDATE Produit p SET p.quantiteStock = p.quantiteStock + :quantite WHERE p.id = :id")
    void updateStock(@Param("id") Long id, @Param("quantite") int quantite);
    
    // Produits avec le plus de retours
    @Query("SELECT p, COUNT(r) as retourCount FROM Produit p LEFT JOIN p.retours r GROUP BY p ORDER BY retourCount DESC")
    List<Object[]> findProductsByReturnCount();
    
    // Produits avec non-conformités
    @Query("SELECT DISTINCT p FROM Produit p JOIN p.nonConformites nc")
    List<Produit> findProductsWithNonConformites();
}
