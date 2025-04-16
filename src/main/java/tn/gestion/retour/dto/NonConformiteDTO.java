package tn.gestion.retour.dto;



import java.sql.Date;

public class NonConformiteDTO {
    private Long id;
    private String description;
    private String gravite;
    private Date date;
    private Long produitId; // Identifiant du produit lié
    private Long retourId;  // Identifiant du retour lié
    
    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getRetourId() {
        return retourId;
    }

    public void setRetourId(Long retourId) {
        this.retourId = retourId;
    }
}
