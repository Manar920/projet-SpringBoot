package tn.gestion.retour.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HistoriqueRetourDTO {
    private Long id;
    private Long retourId;
    private String action;
    private Date date;
    private Long employeId;
}
