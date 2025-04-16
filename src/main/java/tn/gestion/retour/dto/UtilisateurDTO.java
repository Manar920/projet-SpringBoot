package tn.gestion.retour.dto;

import java.util.List;

public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String email;
    private String role; // ADMIN, QUALITE, STOCK, CLIENT
    private List<Long> retoursClientIds;  // IDs des retours du client
    private List<Long> historiquesIds;    // IDs des historiques liés à l'utilisateur

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getRetoursClientIds() {
        return retoursClientIds;
    }

    public void setRetoursClientIds(List<Long> retoursClientIds) {
        this.retoursClientIds = retoursClientIds;
    }

    public List<Long> getHistoriquesIds() {
        return historiquesIds;
    }

    public void setHistoriquesIds(List<Long> historiquesIds) {
        this.historiquesIds = historiquesIds;
    }
}
