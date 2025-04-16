package tn.gestion.retour.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.gestion.retour.dto.UtilisateurDTO;
import tn.gestion.retour.mappers.UtilisateurMapper;
import tn.gestion.retour.models.Utilisateur;
import tn.gestion.retour.services.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateursDTO = utilisateurService.getAllUtilisateurs().stream()
            .map(UtilisateurMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateursDTO);
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable Long id) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            return ResponseEntity.ok(UtilisateurMapper.toDTO(utilisateur));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer un utilisateur par email
    @GetMapping("/email/{email}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurByEmail(@PathVariable String email) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
            return ResponseEntity.ok(UtilisateurMapper.toDTO(utilisateur));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Récupérer les utilisateurs par rôle
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateursByRole(@PathVariable String role) {
        List<UtilisateurDTO> utilisateursDTO = utilisateurService.getUtilisateursByRole(role).stream()
            .map(UtilisateurMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(utilisateursDTO);
    }

    // Récupérer les membres du service qualité
    @GetMapping("/qualite")
    public ResponseEntity<List<UtilisateurDTO>> getQualityTeam() {
        List<UtilisateurDTO> qualityTeamDTO = utilisateurService.getQualityTeam().stream()
            .map(UtilisateurMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(qualityTeamDTO);
    }

    // Récupérer les employés ayant effectué des actions sur les retours
    @GetMapping("/actifs")
    public ResponseEntity<List<UtilisateurDTO>> getActiveEmployees() {
        List<UtilisateurDTO> activeEmployeesDTO = utilisateurService.getActiveEmployees().stream()
            .map(UtilisateurMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(activeEmployeesDTO);
    }

    // Ajouter ou modifier un utilisateur
    @PostMapping
    public ResponseEntity<UtilisateurDTO> saveUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            Utilisateur utilisateur = UtilisateurMapper.toEntity(utilisateurDTO);
            Utilisateur savedUtilisateur = utilisateurService.saveUtilisateur(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).body(UtilisateurMapper.toDTO(savedUtilisateur));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Supprimer un utilisateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        try {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
