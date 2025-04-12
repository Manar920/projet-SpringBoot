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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String email;
    private String role; // ADMIN, QUALITE, STOCK, CLIENT
    
    @OneToMany(mappedBy = "client")
    private List<RetourProduit> retoursClient;
    
    @OneToMany(mappedBy = "employe")
    private List<HistoriqueRetour> historiques;
    
    
}

