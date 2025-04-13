package tn.gestion.retour.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.gestion.retour.models.Utilisateur;
import tn.gestion.retour.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // Enregistrer ou modifier un utilisateur
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé.");
        }
        return utilisateurRepository.save(utilisateur);
    }

    // Supprimer un utilisateur par ID
    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur avec ID " + id + " non trouvé.");
        }
        utilisateurRepository.deleteById(id);
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Trouver un utilisateur par ID
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("Utilisateur avec ID " + id + " non trouvé.")
        );
    }

    // Trouver un utilisateur par email
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email).stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Utilisateur avec email " + email + " non trouvé."));
    }

    // Trouver les utilisateurs par rôle
    public List<Utilisateur> getUtilisateursByRole(String role) {
        return utilisateurRepository.findByRole(role);
    }

    // Trouver les membres du service qualité
    public List<Utilisateur> getQualityTeam() {
        return utilisateurRepository.findQualityTeam();
    }

    // Vérifier si un email existe déjà
    public boolean isEmailExist(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    // Trouver les employés ayant effectué des actions sur les retours
    public List<Utilisateur> getActiveEmployees() {
        return utilisateurRepository.findActiveEmployees();
    }
}
