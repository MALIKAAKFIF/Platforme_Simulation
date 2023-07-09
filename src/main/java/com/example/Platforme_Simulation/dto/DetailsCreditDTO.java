package com.example.Platforme_Simulation.dto;

import com.example.Platforme_Simulation.entity.DemandeCredit;
import jakarta.persistence.JoinColumn;

import javax.persistence.OneToOne;
import java.sql.Date;

public class DetailsCreditDTO {
    private Integer idDetails;
    private Integer idDemande;
    private Float tauxInteret;
    private Integer dureeRemboursement;
    private Date dateDebutRemboursement;
    private Float montantMensuelRemboursement;
    private Float montantTotalAvecInteret;

   private String emploie;
    private double montantDemande;

    public String getEmploie() {
        return emploie;
    }

    public void setEmploie(String emploie) {
        this.emploie = emploie;
    }

    public DetailsCreditDTO() {
    }

    public DetailsCreditDTO(Integer idDetails, Integer idDemande, Float tauxInteret, Integer dureeRemboursement, Date dateDebutRemboursement, Float montantMensuelRemboursement, Float montantTotalAvecInteret) {
        this.idDetails = idDetails;
        this.idDemande = idDemande;
        this.tauxInteret = tauxInteret;
        this.dureeRemboursement = dureeRemboursement;
        this.dateDebutRemboursement = dateDebutRemboursement;
        this.montantMensuelRemboursement = montantMensuelRemboursement;
        this.montantTotalAvecInteret = montantTotalAvecInteret;
    }

    public Integer getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(Integer idDetails) {
        this.idDetails = idDetails;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = Math.toIntExact(idDemande);
    }

    public Float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Integer getDureeRemboursement() {
        return dureeRemboursement;
    }

    public void setDureeRemboursement(Integer dureeRemboursement) {
        this.dureeRemboursement = dureeRemboursement;
    }

    public Date getDateDebutRemboursement() {
        return dateDebutRemboursement;
    }

    public void setDateDebutRemboursement(Date dateDebutRemboursement) {
        this.dateDebutRemboursement = dateDebutRemboursement;
    }

    public Float getMontantMensuelRemboursement() {
        return montantMensuelRemboursement;
    }

    public void setMontantMensuelRemboursement(Float montantMensuelRemboursement) {
        this.montantMensuelRemboursement = montantMensuelRemboursement;
    }

    public Float getMontantTotalAvecInteret() {
        return montantTotalAvecInteret;
    }

    public void setMontantTotalAvecInteret(double montantTotalAvecInteret) {
        this.montantTotalAvecInteret = (float) montantTotalAvecInteret;
    }
    public double getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(double montantDemande) {
        this.montantDemande = montantDemande;
    }
    @OneToOne
    @JoinColumn(name = "idDemande")
    private DemandeCredit idDemandeCredit;

    public DemandeCredit getIdDemandeCredit() {
        return idDemandeCredit;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public void setMontantTotalAvecInteret(Float montantTotalAvecInteret) {
        this.montantTotalAvecInteret = montantTotalAvecInteret;
    }

    public void setIdDemandeCredit(DemandeCredit idDemandeCredit) {
        this.idDemandeCredit = idDemandeCredit;
    }
}
