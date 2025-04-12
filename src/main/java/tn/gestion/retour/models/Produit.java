package tn.gestion.retour.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference;
    private String designation;
    private int quantiteStock;
    
    @OneToMany(mappedBy = "produit")
    private List<RetourProduit> retours;
    
    @OneToMany(mappedBy = "produit")
    private List<NonConformite> nonConformites;
    
}
