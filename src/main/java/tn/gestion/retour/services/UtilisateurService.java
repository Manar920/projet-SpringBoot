package tn.gestion.retour.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.gestion.retour.models.Utilisateur;
import tn.gestion.retour.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Enregistrer ou modifier un utilisateur
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        // Si nouvel utilisateur
        if (utilisateur.getId() == null) {
            if (utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
                throw new IllegalArgumentException("Email déjà utilisé.");
            }
        } else {
            // Si modification, vérifier s'il existe et que l'email appartient bien à lui
            Utilisateur existing = utilisateurRepository.findById(utilisateur.getId()).orElseThrow(() ->
                new IllegalArgumentException("Utilisateur avec ID " + utilisateur.getId() + " non trouvé.")
            );
            if (!existing.getEmail().equals(utilisateur.getEmail()) &&
                utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
                throw new IllegalArgumentException("Email déjà utilisé par un autre utilisateur.");
            }
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
