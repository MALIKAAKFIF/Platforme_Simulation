package com.example.Platforme_Simulation.entity;

import jakarta.persistence.*;

@Entity
public class CreditSimulation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double creditAmount;
    private int duration;


private String emploie;
    public Long getId() {
        return id;
    }


    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEmploie() {
        return emploie;
    }

    public void setEmploie(String emploie) {
        this.emploie = emploie;
    }


}