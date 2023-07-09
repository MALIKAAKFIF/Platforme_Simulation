package com.example.Platforme_Simulation.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "radCodeAgence")
public class RadCodeAgence {

    @Id
    @Column(name = "codeAgence")
    private String codeAgence;

    @Column(name = "radical")
    private String radical;


    public RadCodeAgence() {
    }

    public RadCodeAgence(String codeAgence, String radical) {
        this.codeAgence = codeAgence;
        this.radical = radical;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public String getRadical() {
        return radical;
    }

    public void setRadical(String radical) {
        this.radical = radical;
    }
}
