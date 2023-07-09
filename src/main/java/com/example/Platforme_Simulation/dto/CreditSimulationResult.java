package com.example.Platforme_Simulation.dto;

public class CreditSimulationResult {
    private double creditAmount;
    private int duration;
    private String emploie;
    private double monthlyPayment;

    //Constructeur, getters et setters

    public CreditSimulationResult() {
    }

    public CreditSimulationResult(double creditAmount, int duration, String emploie, double monthlyPayment) {
        this.creditAmount = creditAmount;
        this.duration = duration;
        this.emploie = emploie;
        this.monthlyPayment = monthlyPayment;
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

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEmploie() {
        return emploie;
    }

    public void setEmploie (String emploie) {
        this.emploie = emploie;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {

        this.monthlyPayment = monthlyPayment;
    }
}