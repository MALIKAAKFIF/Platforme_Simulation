package com.example.Platforme_Simulation.service;

import com.example.Platforme_Simulation.entity.DemandeCredit;
import com.example.Platforme_Simulation.entity.DetailsCredit;
import com.example.Platforme_Simulation.repository.DemandeCreditRepository;
import com.example.Platforme_Simulation.repository.DetailsCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailsCreditService {
@Autowired
    private final DetailsCreditRepository detailsCreditRepository;
    DemandeCreditRepository demandeCreditRepository;


    public DetailsCreditService(DetailsCreditRepository detailsCreditRepository) {
        this.detailsCreditRepository = detailsCreditRepository;
    }

    public DetailsCredit saveDetailsCredit(DetailsCredit detailsCredit,Integer demandeCreditId) {


        DemandeCredit demandeCredit = demandeCreditRepository.findById(demandeCreditId)
                .orElseThrow(() -> new IllegalArgumentException("Demande_Credit entity not found"));

        detailsCredit.setDureeRemboursement(detailsCredit.getDureeRemboursement());
        detailsCredit.setDateDebutRemboursement(detailsCredit.getDateDebutRemboursement());
        detailsCredit.setTauxInteret(detailsCredit.getTauxInteret());
        detailsCredit.setMontantMensuelRemboursement(detailsCredit.getMontantMensuelRemboursement());
        detailsCredit.setMontantTotalAvecInteret(detailsCredit.getMontantTotalAvecInteret());
        return detailsCreditRepository.save(detailsCredit);
    }

    public DetailsCredit getDetailsCreditById(Integer id) {
        return detailsCreditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetailsCredit entity not found"));
    }

}
