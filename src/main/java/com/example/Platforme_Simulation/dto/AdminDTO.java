package com.example.Platforme_Simulation.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

public class AdminDTO {


    private String matricule;

    private String password;


    private String nom;


    private String prenom;

    public AdminDTO() {
    }

    public AdminDTO( String matricule, String password) {

        this.matricule = matricule;
        this.password = password;
    }



    public String getMatricule() {
        return matricule;
    }

    public String getPassword() {
        return password;
    }



    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

