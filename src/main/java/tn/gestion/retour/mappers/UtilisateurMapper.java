package tn.gestion.retour.mappers;

import tn.gestion.retour.dto.UtilisateurDTO;
import tn.gestion.retour.models.Utilisateur;

import java.util.List;
import java.util.stream.Collectors;

public class UtilisateurMapper {

    // Convertir l'entité Utilisateur en DTO
    public static UtilisateurDTO toDTO(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setRole(utilisateur.getRole());
        
        // Convertir les listes de retours et historiques en listes d'IDs
        List<Long> retoursClientIds = utilisateur.getRetoursClient().stream()
                                                 .map(retour -> retour.getId())
                                                 .collect(Collectors.toList());
        utilisateurDTO.setRetoursClientIds(retoursClientIds);

        List<Long> historiquesIds = utilisateur.getHistoriques().stream()
                                               .map(historique -> historique.getId())
                                               .collect(Collectors.toList());
        utilisateurDTO.setHistoriquesIds(historiquesIds);

        return utilisateurDTO;
    }

    // Convertir un DTO en entité Utilisateur
    public static Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDTO.getId());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setRole(utilisateurDTO.getRole());
        
        // Ici, vous pouvez gérer les relations (retoursClient, historiques) si nécessaire
        // Mais comme nous travaillons avec des IDs dans le DTO, vous devrez récupérer les entités
        // associées dans la couche service si vous avez besoin de les remplir.

        return utilisateur;
    }
}
