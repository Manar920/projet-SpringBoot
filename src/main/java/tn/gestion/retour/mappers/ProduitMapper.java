package tn.gestion.retour.mappers;

import org.springframework.stereotype.Component;

import tn.gestion.retour.models.Produit;
import tn.gestion.retour.dto.ProduitDTO;

@Component
public class ProduitMapper {

    public ProduitDTO toDTO(Produit produit) {
        if (produit == null) return null;

        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setReference(produit.getReference());
        dto.setDesignation(produit.getDesignation());
        dto.setQuantiteStock(produit.getQuantiteStock());
        return dto;
    }

    public Produit toEntity(ProduitDTO dto) {
        if (dto == null) return null;

        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setReference(dto.getReference());
        produit.setDesignation(dto.getDesignation());
        produit.setQuantiteStock(dto.getQuantiteStock());
        return produit;
    }
}
