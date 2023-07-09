package com.example.Platforme_Simulation.controller;

import com.example.Platforme_Simulation.repository.DemandeCreditRepository;
import com.example.Platforme_Simulation.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private DemandeCreditRepository demandeCreditRepository;
@Autowired
private DossierRepository dossierRepository;
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        long totalDocuments = demandeCreditRepository.count();
        long acceptedDocuments = demandeCreditRepository.countByStatut("Accepté");
        long rejectedDocuments = demandeCreditRepository.countByStatut("Rejeté");
        long documentEnCours= demandeCreditRepository.countByStatut("En cours");


        model.addAttribute("totalDocuments", totalDocuments);
        model.addAttribute("acceptedDocuments", acceptedDocuments);
        model.addAttribute("rejectedDocuments", rejectedDocuments);
        model.addAttribute("documentEnCours", documentEnCours);

        return "dashboard";
    }
}
