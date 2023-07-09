package com.example.Platforme_Simulation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


import java.sql.Date;
import java.util.List;

@Entity
@Table(name="Demande_Credit")
public class DemandeCredit {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_demande")
    public Long ID_demande;
    @Column(name="montantDemande", nullable=false)
    private Float montantDemande;

    @Column(name="DateDemande")
    private Date dateDemande;
    @Column(name="Statut", nullable=true)
    private String statut;
@Column(name="montant_credit_une_etabl_credit",nullable = true)
private double montantCreditAutreEtabliCredit;


    public Long getID_demande() {
        return Long.valueOf(ID_demande);
    }

    public Float getMontantDemande() {
        return montantDemande;
    }



    public Date getDateDemande() {
        return dateDemande;
    }

    public String getStatut() {
        return statut;
    }



    public void setID_demande(Long ID_demande) {
        this.ID_demande = ID_demande;
    }

    public void setMontantDemande(Float montantDemande) {
        this.montantDemande = montantDemande;
    }


    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @ManyToOne
    @JoinColumn(name = "utilisateur_id",nullable = true)
    private Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public double getMontantCreditAutreEtabliCredit() {
        return montantCreditAutreEtabliCredit;
    }

    public void setMontantCreditAutreEtabliCredit(double montantCreditAutreEtabliCredit) {
        this.montantCreditAutreEtabliCredit = montantCreditAutreEtabliCredit;
    }
    @OneToOne(mappedBy = "demandeCredit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public DetailsCredit detailsCredit;

    public void setDetailsCredit(DetailsCredit detailsCredit) {
        this.detailsCredit = detailsCredit;
    }

    public DetailsCredit getDetailsCredit() {
        return detailsCredit;
    }
}

