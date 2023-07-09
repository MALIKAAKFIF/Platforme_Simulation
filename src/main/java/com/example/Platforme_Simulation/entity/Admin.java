package com.example.Platforme_Simulation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "ccl")
public class Admin {

    @Id
    @Column(name = "matricule")
    private String matricule;

    @Column(name = "password")
    private String password;

    @Column(name = "Nom", nullable = false)
    @NotEmpty(message = "Le nom d'utilisateur ne peut pas être vide.")
    private String nom;

    @Column(name = "Prenom", nullable = false)
    private String prenom;
@Column(name = "code_agence")
private String code_agence;

    public String getCode_agence() {
        return code_agence;
    }

    public void setCode_agence(String code_agence) {
        this.code_agence = code_agence;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
