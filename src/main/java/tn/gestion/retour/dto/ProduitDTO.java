package tn.gestion.retour.dto;

import lombok.Data;

@Data
public class ProduitDTO {
    private Long id;
    private String reference;
    private String designation;
    private int quantiteStock;
}
