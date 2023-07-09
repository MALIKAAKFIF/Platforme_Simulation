package com.example.Platforme_Simulation.dto;

public class InterfaceAdmineDTO {
    public Long id;
    public int radical;

    public String cheminDocuments;
    public String email;
    public String statut;

    public InterfaceAdmineDTO(int id) {
        this.id = (long) id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public int getRadical() {
        return radical;
    }



    public String getCheminDocuments() {
        return cheminDocuments;
    }

    public void setRadical(int radical) {
        this.radical = radical;
    }


    public void setCheminDocuments(String cheminDocuments) {
        this.cheminDocuments = cheminDocuments;
    }
}

