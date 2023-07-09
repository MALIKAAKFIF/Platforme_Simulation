package com.example.Platforme_Simulation.dto;


public class AdresseDTO {

    private Integer idAdresse;
    private String adresse;
    private String ville;
    private String codePostal;



    // Constructeurs, getters et setters

    public AdresseDTO() {
    }

    public AdresseDTO(Integer idAdresse, String adresse, String ville, String codePostal) {
        this.idAdresse = idAdresse;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}
