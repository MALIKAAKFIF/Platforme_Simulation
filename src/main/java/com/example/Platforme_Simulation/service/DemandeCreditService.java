package com.example.Platforme_Simulation.service;


import com.example.Platforme_Simulation.entity.DemandeCredit;
import com.example.Platforme_Simulation.entity.Utilisateur;
import com.example.Platforme_Simulation.repository.DemandeCreditRepository;
import com.example.Platforme_Simulation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemandeCreditService {

    private final DemandeCreditRepository demandeCreditRepository;
    @Autowired
private final UtilisateurRepository utilisateurRepository;
    @Autowired
    public DemandeCreditService(DemandeCreditRepository demandeCreditRepository, UtilisateurRepository utilisateurRepository) {
        this.demandeCreditRepository = demandeCreditRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public DemandeCredit saveDemandeCredit(DemandeCredit demandeCredit, Integer utisateurID) {
        Utilisateur utilisateur= utilisateurRepository.findById(utisateurID)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur entity not found"));

        demandeCredit.setMontantDemande(demandeCredit.getMontantDemande());
        demandeCredit.setStatut(demandeCredit.getStatut());

        return demandeCreditRepository.save(demandeCredit);
    }

    public DemandeCredit getDemandeCreditById(Integer id) {
        return demandeCreditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Demande_Credit entity not found"));
    }
    public void updateStatus(Long utilisateurId, Long demandeCreditId, String newStatus) {
        // Récupérer la demande de crédit à partir de l'ID
        Optional<DemandeCredit> demandeCreditOptional = demandeCreditRepository.findById(Math.toIntExact(demandeCreditId));
        if (demandeCreditOptional.isPresent()) {
            DemandeCredit demandeCredit = demandeCreditOptional.get();
            demandeCredit.setStatut(newStatus);
            demandeCreditRepository.save(demandeCredit);
        }
    }
    public DemandeCredit findLatestDemandeCreditByUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(Math.toIntExact(utilisateurId)).orElse(null);
        if (utilisateur != null) {
            return demandeCreditRepository.findTopByUtilisateurOrderByDateDemandeDesc(utilisateur);
        }
        return null;
    }

}
