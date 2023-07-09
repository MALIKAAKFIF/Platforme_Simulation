package com.example.Platforme_Simulation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dossier")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code_agence")
    private Integer codeAgence;

    @Column(name = "status")
    public String status;

    @ManyToOne
    @JoinColumn(name = "demande_credit_id")
    private DemandeCredit demandeCredit;
    @OneToOne
    @JoinColumn(name = "document_support_id")
    private DocumentSupport documentSupport;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @OneToOne
    @JoinColumn(name = "details_credit_id")
    private DetailsCredit detailsCredit;
    public DemandeCredit getDemandeCredit() {
        return demandeCredit;
    }
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setDemandeCredit(DemandeCredit demandeCredit) {
        this.demandeCredit = demandeCredit;
    }

    public Long getId() {
        return id;
    }

    public Integer getCodeAgence() {
        return codeAgence;
    }

    public String getStatus() {
        return status;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCodeAgence(Integer codeAgence) {
        this.codeAgence = codeAgence;
    }

    public void setStatus(String status) {
        this.status = status;
    }





    public DocumentSupport getDocumentSupport() {
        return documentSupport;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setDocumentSupport(DocumentSupport documentSupport) {
        this.documentSupport = documentSupport;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public DetailsCredit getDetailsCredit() {
        return detailsCredit;
    }

    public void setDetailsCredit(DetailsCredit detailsCredit) {
        this.detailsCredit = detailsCredit;
    }
}
