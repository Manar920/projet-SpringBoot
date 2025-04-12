package tn.gestion.retour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.gestion.retour.models.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
    // Trouver un utilisateur par email
    List<Utilisateur> findByEmail(String email);
    
    // Trouver les utilisateurs par rôle
    List<Utilisateur> findByRole(String role);
    
    // Trouver les membres du service qualité
    @Query("SELECT u FROM Utilisateur u WHERE u.role = 'QUALITE'")
    List<Utilisateur> findQualityTeam();
    
    // Vérifier si un email existe déjà
    boolean existsByEmail(String email);
    
    // Trouver les utilisateurs ayant effectué des actions sur les retours
    @Query("SELECT DISTINCT h.employe FROM HistoriqueRetour h")
    List<Utilisateur> findActiveEmployees();
}
