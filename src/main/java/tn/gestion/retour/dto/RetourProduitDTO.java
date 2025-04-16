package tn.gestion.retour.dto;

import java.sql.Date;
import java.util.List;

public class RetourProduitDTO {

    private Long id;
    private Long produitId;
    private Long clientId;
    private String raison;
    private String etatTraitement;
    private Date date;
    private List<Long> historiqueIds;
    private Long nonConformiteId;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getEtatTraitement() {
        return etatTraitement;
    }

    public void setEtatTraitement(String etatTraitement) {
        this.etatTraitement = etatTraitement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getHistoriqueIds() {
        return historiqueIds;
    }

    public void setHistoriqueIds(List<Long> historiqueIds) {
        this.historiqueIds = historiqueIds;
    }

    public Long getNonConformiteId() {
        return nonConformiteId;
    }

    public void setNonConformiteId(Long nonConformiteId) {
        this.nonConformiteId = nonConformiteId;
    }
}
