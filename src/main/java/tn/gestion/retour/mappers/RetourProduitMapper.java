package tn.gestion.retour.mappers;

import tn.gestion.retour.models.RetourProduit;
import tn.gestion.retour.dto.RetourProduitDTO;
import tn.gestion.retour.models.HistoriqueRetour;

import java.util.List;
import java.util.stream.Collectors;

public class RetourProduitMapper {

    // Convertir l'entité RetourProduit en DTO
    public static RetourProduitDTO toDTO(RetourProduit retourProduit) {
        RetourProduitDTO dto = new RetourProduitDTO();
        dto.setId(retourProduit.getId());
        dto.setProduitId(retourProduit.getProduit().getId());  // Assumant que Produit a un id
        dto.setClientId(retourProduit.getClient().getId());  // Assumant que Utilisateur a un id
        dto.setRaison(retourProduit.getRaison());
        dto.setEtatTraitement(retourProduit.getEtatTraitement());
        dto.setDate(retourProduit.getDate());
        
        // Mapper les IDs des historiques
        List<Long> historiqueIds = retourProduit.getHistoriques().stream()
                                                .map(HistoriqueRetour::getId)
                                                .collect(Collectors.toList());
        dto.setHistoriqueIds(historiqueIds);

        // Mapper l'ID de NonConformite, s'il existe
        if (retourProduit.getNonConformite() != null) {
            dto.setNonConformiteId(retourProduit.getNonConformite().getId());
        }

        return dto;
    }

    // Convertir le DTO en entité RetourProduit
    public static RetourProduit toEntity(RetourProduitDTO dto) {
        RetourProduit retourProduit = new RetourProduit();
        // Remplir avec les valeurs du DTO
        retourProduit.setId(dto.getId());
        // Vous devrez récupérer les objets Produit, Utilisateur, HistoriqueRetour, NonConformite
        // en fonction des IDs depuis la base de données ou un service
        // retourProduit.setProduit(...);
        // retourProduit.setClient(...);
        retourProduit.setRaison(dto.getRaison());
        retourProduit.setEtatTraitement(dto.getEtatTraitement());
        retourProduit.setDate(dto.getDate());
        
        // Mapper les historiques (ici vous avez juste les IDs, donc à compléter selon la logique de votre application)
        // retourProduit.setHistoriques(...);

        return retourProduit;
    }
}
