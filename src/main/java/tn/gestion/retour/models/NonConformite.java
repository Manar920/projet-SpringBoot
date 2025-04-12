package tn.gestion.retour.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class NonConformite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String description;
    private String gravite; // FAIBLE, MOYENNE, ELEVEE
    private Date date;
    
    @ManyToOne
    private Produit produit;
    
    @OneToOne
    private RetourProduit retour;
    
}
