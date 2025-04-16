package tn.gestion.retour.mappers;

import org.springframework.stereotype.Component;

import tn.gestion.retour.dto.HistoriqueRetourDTO;
import tn.gestion.retour.models.HistoriqueRetour;
import tn.gestion.retour.models.RetourProduit;
import tn.gestion.retour.models.Utilisateur;

@Component
public class HistoriqueRetourMapper {

    public HistoriqueRetourDTO toDTO(HistoriqueRetour entity) {
        HistoriqueRetourDTO dto = new HistoriqueRetourDTO();
        dto.setId(entity.getId());
        dto.setAction(entity.getAction());
        dto.setDate(entity.getDate());

        if (entity.getRetour() != null) {
            dto.setRetourId(entity.getRetour().getId());
        }

        if (entity.getEmploye() != null) {
            dto.setEmployeId(entity.getEmploye().getId());
        }

        return dto;
    }

    public HistoriqueRetour toEntity(HistoriqueRetourDTO dto) {
        HistoriqueRetour entity = new HistoriqueRetour();
        entity.setId(dto.getId());
        entity.setAction(dto.getAction());
        entity.setDate(dto.getDate());

        if (dto.getRetourId() != null) {
            RetourProduit retour = new RetourProduit();
            retour.setId(dto.getRetourId());
            entity.setRetour(retour);
        }

        if (dto.getEmployeId() != null) {
            Utilisateur employe = new Utilisateur();
            employe.setId(dto.getEmployeId());
            entity.setEmploye(employe);
        }

        return entity;
    }
}

