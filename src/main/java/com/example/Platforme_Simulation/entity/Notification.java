package com.example.Platforme_Simulation.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_notification")
    private Integer idNotification;

    @Column(name = "ID_dossier", nullable = false)
    private Integer idDossier;

    @Column(name = "Message")
    private String message;

    @Column(name = "Date_notification")
    private Date dateNotification;

    @Column(name = "Lue", nullable = false)
    private Boolean lue;

    // Constructeurs, getters et setters

    public Notification() {
    }

    public Notification(Integer idDossier, String message, Date dateNotification, Boolean lue) {
        this.idDossier = idDossier;
        this.message = message;
        this.dateNotification = dateNotification;
        this.lue = lue;
    }

    // Getter et Setter pour idNotification

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    // Getters et Setters pour les autres attributs


    public Integer getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(Integer idDossier) {
        this.idDossier = idDossier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    public Boolean getLue() {
        return lue;
    }

    public void setLue(Boolean lue) {
        this.lue = lue;
    }


}
