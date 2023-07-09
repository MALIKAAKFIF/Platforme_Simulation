package com.example.Platforme_Simulation.dto;

import com.example.Platforme_Simulation.entity.Utilisateur;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

public class DemandeCreditDTO {
    private Float montantDemande;
    private String motif;
    private Date dateDemande;
    private String statut;
    private Long utilisateurId;


    private double montantCreditAutreEtabliCredit;

    public double getMontantCreditAutreEtabliCredit() {
        return montantCreditAutreEtabliCredit;
    }

    public void setMontantCreditAutreEtabliCredit(double montantCreditAutreEtabliCredit) {
        this.montantCreditAutreEtabliCredit = montantCreditAutreEtabliCredit;
    }

    public DemandeCreditDTO() {
    }

    public DemandeCreditDTO(Float montantDemande, String motif, Date dateDemande, String statut, Long utilisateurId, String emploie) {
        this.montantDemande = montantDemande;
        this.motif = motif;
        this.dateDemande = dateDemande;
        this.statut = statut;
        this.utilisateurId = utilisateurId;

    }

    public Float getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(Float montantDemande) {
        this.montantDemande = montantDemande;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }


}
