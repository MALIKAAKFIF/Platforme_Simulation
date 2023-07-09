package com.example.Platforme_Simulation.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_utilisateur")
    private Long idUtilisateur;

    @Column(name = "Nom", nullable = false)
    @NotEmpty(message = "Le nom d'utilisateur ne peut pas être vide.")

    private String nom;

    @Column(name = "Prenom", nullable = false)
    private String prenom;

    @Column(name = "Email", unique = true, nullable = false)
    @Email(message = "Adresse e-mail invalide.")

    private String email;

    @Column(name = "Mot_de_passe", nullable = false)
    @Size(min = 6, message = "Le mot de passe doit comporter au moins 6 caractères.")

    private String motDePasse;
    @Column(name = "Emploi")
    private String emploi;

    @Column(name = "Radical", length = 50)
    private String radical;



    public Long getIdUtilisateur() {
        return Long.valueOf(idUtilisateur);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }


    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }



    public String getRadical() {
        return radical;
    }


    public void setRadical(String radical) {
        this.radical = radical;
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID_utilisateur")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )


    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUtilisateurs().add(this);
    }



    public void removeRole(Role role) {
        roles.remove(role);
        role.getUtilisateurs().remove(this);
    }



    public void setEmploi(String emploi) {
        this.emploi = emploi;
    }

    @OneToMany(mappedBy = "utilisateur")
    private List<DemandeCredit> demandesCredit;

    public List<DemandeCredit> getDemandesCredit() {
        return demandesCredit;
    }
    public void setDemandesCredit(List<DemandeCredit> demandesCredit) {
        this.demandesCredit = demandesCredit;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Adresse adresse;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    public List<Dossier> dossiers;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    public List<DemandeCredit> demandeCredits;

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    public void setDemandeCredits(List<DemandeCredit> demandeCredits) {
        this.demandeCredits = demandeCredits;
    }
}
