package com.example.Platforme_Simulation.entity;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Details_Credit")
public class DetailsCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_details")
    private long idDetails;



    @Column(name = "Taux_interet")
    private Float tauxInteret;

    @Column(name = "Duree_remboursement")
    private Integer dureeRemboursement;

    @Column(name = "Date_debut_remboursement")
    private Date dateDebutRemboursement;

    @Column(name = "Montant_mensuel_remboursement")
    private Float montantMensuelRemboursement;

    @Column(name = "Montant_total_avec_interet")
    private Float montantTotalAvecInteret;
@Column(name = "revenuMensuel")
private double revenuMensuel;
@Column(name = "telephone")
private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String emploie;

    public String getEmploie() {
        return emploie;
    }

    public void setEmploie(String emploie) {
        this.emploie = emploie;
    }

    public DetailsCredit() {
    }

    public DetailsCredit(Long idDemande, Float tauxInteret, Integer dureeRemboursement,
                         Date dateDebutRemboursement, Float montantMensuelRemboursement,
                         Float montantTotalAvecInteret) {

        this.tauxInteret = tauxInteret;
        this.dureeRemboursement = dureeRemboursement;
        this.dateDebutRemboursement = dateDebutRemboursement;
        this.montantMensuelRemboursement = montantMensuelRemboursement;
        this.montantTotalAvecInteret = montantTotalAvecInteret;
    }

    // Getter et Setter pour idDetails

    public Long getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(Integer idDetails) {
        this.idDetails = idDetails;
    }

    // Getters et Setters pour les autres attributs



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

    public void setMontantTotalAvecInteret(Float montantTotalAvecInteret) {
        this.montantTotalAvecInteret = montantTotalAvecInteret;
    }











    public void setIdDetails(long idDetails) {
        this.idDetails = idDetails;
    }




    @OneToOne
    @JoinColumn(name = "demande_credit_id")
    private DemandeCredit demandeCredit;

    public DemandeCredit getDemandeCredit() {
        return demandeCredit;
    }

    public void setDemandeCredit(DemandeCredit demandeCredit) {
        this.demandeCredit = demandeCredit;
    }

    public double getRevenuMensuel() {
        return revenuMensuel;
    }

    public void setRevenuMensuel(double revenuMensuel) {
        this.revenuMensuel = revenuMensuel;
    }
}
