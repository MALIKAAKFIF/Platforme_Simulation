package com.example.Platforme_Simulation.dto;


public class UtilisateurDTO {

    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String emploi;
    private String radical;
    private AdresseDTO adresse;

    private String numTelephone;

    // Constructeurs, getters et setters

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(Integer idUtilisateur, String nom, String prenom, String email, String motDePasse, String emploi, String radical, AdresseDTO adresse, String numTelephone) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.emploi = emploi;
        this.radical = radical;
        this.adresse = adresse;
        this.numTelephone = numTelephone;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public void setEmplie(String emploi) {
        this.emploi = emploi;
    }

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }

    public AdresseDTO getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDTO adresse) {
        this.adresse = adresse;
    }

    public String getEmploi() {
        return emploi;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setEmploi(String emploi) {
        this.emploi = emploi;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }
}

