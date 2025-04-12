package tn.gestion.retour.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class RetourProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Produit produit;
    
    @ManyToOne
    private Utilisateur client;
    
    private String raison;
    private String etatTraitement; // EN_ATTENTE, VALIDE, REFUSE, TRAITE
    private Date date;
    
    @OneToMany(mappedBy = "retour")
    private List<HistoriqueRetour> historiques;
    
    @OneToOne(mappedBy = "retour")
    private NonConformite nonConformite;

	
    
}
