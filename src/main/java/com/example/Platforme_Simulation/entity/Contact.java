package com.example.Platforme_Simulation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String nom;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String message;

    // Getters et setters

    // Constructeur par défaut et autres méthodes si nécessaire
}
