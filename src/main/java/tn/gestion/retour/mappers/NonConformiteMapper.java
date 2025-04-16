package tn.gestion.retour.mappers;

import tn.gestion.retour.dto.NonConformiteDTO;
import tn.gestion.retour.models.NonConformite;

public class NonConformiteMapper {

    // Convertir l'entité NonConformite en NonConformiteDTO
    public static NonConformiteDTO toDTO(NonConformite nonConformite) {
        if (nonConformite == null) {
            return null;
        }

        NonConformiteDTO dto = new NonConformiteDTO();
        dto.setId(nonConformite.getId());
        dto.setDescription(nonConformite.getDescription());
        dto.setGravite(nonConformite.getGravite());
        dto.setDate(nonConformite.getDate());

        // Mapper les IDs des entités liées
        dto.setProduitId(nonConformite.getProduit() != null ? nonConformite.getProduit().getId() : null);
        dto.setRetourId(nonConformite.getRetour() != null ? nonConformite.getRetour().getId() : null);

        return dto;
    }

    // Convertir un NonConformiteDTO en entité NonConformite
    public static NonConformite toEntity(NonConformiteDTO dto) {
        if (dto == null) {
            return null;
        }

        NonConformite nonConformite = new NonConformite();
        nonConformite.setId(dto.getId());
        nonConformite.setDescription(dto.getDescription());
        nonConformite.setGravite(dto.getGravite());
        nonConformite.setDate(dto.getDate());

        // Ne pas oublier de mapper les entités liées
        // Ici, on suppose qu'il y a une méthode pour retrouver le produit et le retour
        // en fonction de leurs ID, mais cela dépend de ton architecture.
        // par exemple :
        // nonConformite.setProduit(produitRepository.findById(dto.getProduitId()).orElse(null));
        // nonConformite.setRetour(retourRepository.findById(dto.getRetourId()).orElse(null));

        return nonConformite;
    }
}
