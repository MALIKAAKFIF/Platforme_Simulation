package com.example.Platforme_Simulation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

@Entity
@Table(name = "Documents_Support")
public class DocumentSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_document")

    private Long documentId;
private  Long idDemande;

    @Column(name = "Type_document")
    private String type;

    @Column(name = "CheminDocumentAttestationSalaire")
    @NotEmpty(message = "ce champs ne peut pas etre vide")
    public String cheminDocumentAttestationSalaire;

    @Column(name = "CheminDocumentBulletinPaie")
    public String cheminDocumentBulletinPaie;

    @Column(name = "CheminDocumentDeuxiemeBulletinPaie")
    private String cheminDocumentDeuxiemeBulletinPaie;

    @Column(name = "CheminDocumentCinRecto")
    private String cheminDocumentCinRecto;

    @Column(name = "CheminDocumentCinVerso")
    private String cheminDocumentCinVerso;

    @Column(name = "Date_upload", nullable = false)
    private Date dateUpload;

    // Constructeurs, getters et setters

    public DocumentSupport() {
    }

    public DocumentSupport(Integer idDemande, String typeDocument, String cheminDocumentAttestationSalaire,
                           String cheminDocumentBulletinPaie, String cheminDocumentDeuxiemeBulletinPaie,
                           String cheminDocumentCinRecto, String cheminDocumentCinVerso, Date dateUpload) {

        this.type = typeDocument;
        this.cheminDocumentAttestationSalaire = cheminDocumentAttestationSalaire;
        this.cheminDocumentBulletinPaie = cheminDocumentBulletinPaie;
        this.cheminDocumentDeuxiemeBulletinPaie = cheminDocumentDeuxiemeBulletinPaie;
        this.cheminDocumentCinRecto = cheminDocumentCinRecto;
        this.cheminDocumentCinVerso = cheminDocumentCinVerso;
        this.dateUpload = dateUpload;
    }

    // Getter et Setter pour idDocument



    // Getters et Setters pour les autres attributs



    public String getTypeDocument() {
        return type;
    }

    public void setTypeDocument(String typeDocument) {
        this.type = typeDocument;
    }

    public String getCheminDocumentAttestationSalaire() {
        return cheminDocumentAttestationSalaire;
    }

    public void setCheminDocumentAttestationSalaire(String cheminDocumentAttestationSalaire) {
        this.cheminDocumentAttestationSalaire = cheminDocumentAttestationSalaire;
    }

    public String getCheminDocumentBulletinPaie() {
        return cheminDocumentBulletinPaie;
    }

    public void setCheminDocumentBulletinPaie(String cheminDocumentBulletinPaie) {
        this.cheminDocumentBulletinPaie = cheminDocumentBulletinPaie;
    }

    public String getCheminDocumentDeuxiemeBulletinPaie() {
        return cheminDocumentDeuxiemeBulletinPaie;
    }

    public void setCheminDocumentDeuxiemeBulletinPaie(String cheminDocumentDeuxiemeBulletinPaie) {
        this.cheminDocumentDeuxiemeBulletinPaie = cheminDocumentDeuxiemeBulletinPaie;
    }

    public String getCheminDocumentCinRecto() {
        return cheminDocumentCinRecto;
    }

    public void setCheminDocumentCinRecto(String cheminDocumentCinRecto) {
        this.cheminDocumentCinRecto = cheminDocumentCinRecto;
    }

    public String getCheminDocumentCinVerso() {
        return cheminDocumentCinVerso;
    }

    public void setCheminDocumentCinVerso(String cheminDocumentCinVerso) {
        this.cheminDocumentCinVerso = cheminDocumentCinVerso;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }





    @ManyToOne
    @JoinColumn(name = "demande_credit_id")
    private DemandeCredit demandeCredit;

    public DemandeCredit getDemandeCredit() {
        return demandeCredit;
    }

    public void setDemandeCredit(DemandeCredit demandeCredit) {
        this.demandeCredit = demandeCredit;
    }

    private String path;

    public String getPath() {
        return path;
    }
}
