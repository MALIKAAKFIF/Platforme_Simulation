package com.example.Platforme_Simulation.dto;

import javax.persistence.Column;

public class DossierDTO {
    public Long id;
    private Long documentSupportId;
    private Long demandeCreditId;
    private Long utilisateurId;
    private Integer codeAgence;

    private Long detailsCreditId;

    private Long adresse;
    public DossierDTO() {
    }

    public DossierDTO(Long documentSupportId, Long demandeCreditId, Long utilisateurId, Integer codeAgence, Long detailsCreditId) {
        this.documentSupportId = documentSupportId;
        this.demandeCreditId = demandeCreditId;
        this.utilisateurId = utilisateurId;
        this.codeAgence = codeAgence;
        this.detailsCreditId = detailsCreditId;
    }

    public Long getDocumentSupportId() {
        return documentSupportId;
    }

    public Long getDemandeCreditId() {
        return demandeCreditId;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public Integer getCodeAgence() {
        return codeAgence;
    }

    public void setDocumentSupportId(Long documentSupportId) {
        this.documentSupportId = documentSupportId;
    }

    public void setDemandeCreditId(Long demandeCreditId) {
        this.demandeCreditId = demandeCreditId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setCodeAgence(Integer codeAgence) {
        this.codeAgence = codeAgence;
    }

    public Long getDetailsCreditId() {
        return detailsCreditId;
    }

    public void setAdresse(Long adresse) {
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDetailsCreditId(Long detailsCreditId) {
        this.detailsCreditId = detailsCreditId;
    }
}
