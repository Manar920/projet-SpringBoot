package tn.gestion.retour.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class HistoriqueRetour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private RetourProduit retour;
    
    private String action; // CREATION, VALIDATION, REFUS, TRAITEMENT, MISE_A_JOUR_STOCK
    private Date date;
    
    @ManyToOne
    private Utilisateur employe;
    
    
     
    
}
