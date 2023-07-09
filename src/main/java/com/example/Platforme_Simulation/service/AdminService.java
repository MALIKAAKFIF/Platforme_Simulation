package com.example.Platforme_Simulation.service;

import com.example.Platforme_Simulation.dto.AdminDTO;
import com.example.Platforme_Simulation.entity.*;

import java.util.List;

public interface AdminService {
    void saveAdmin(Admin adminDTO);






    Admin findByMatricule(String matricule);

    List<AdminDTO> findAllUsers();

    public List<String> getRadicauxByMatricule(String matricule);

    public List<Utilisateur> findUtilisateursByRadicaux(List<String> radicaux);
    public List<Dossier> getDossiersByUtilisateurId(Long utilisateurId);
    public Adresse getAdresseByUtilisateurId(Long utilisateurId);
    public List<DemandeCredit> getDemandeByUtilisateurId(Long utilisateurId);
    public DetailsCredit getDetailsCreditByDemandeCreditId(Long demandeCreditId);
    public DemandeCredit updateDossierStatus(Long dossierId, String newStatus);
    public String getStatutDossier(Long id);
    public Long getDossierIdByDemandeCreditId(Long demandeCreditId);
    public List<Long> getIdDossiersByUtilisateurId(Long utilisateurId);
}
