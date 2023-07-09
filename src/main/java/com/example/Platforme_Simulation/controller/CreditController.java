package com.example.Platforme_Simulation.controller;


import com.example.Platforme_Simulation.dto.CreditSimulationResult;
import com.example.Platforme_Simulation.entity.CreditSimulation;
import com.example.Platforme_Simulation.repository.DemandeCreditRepository;
import com.example.Platforme_Simulation.repository.DetailsCreditRepository;
import com.example.Platforme_Simulation.service.CreditSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreditController {
    @Autowired
    private CreditSimulationService creditSimulationService;
    @Autowired
    private DemandeCreditRepository demandeCreditRepository;

    @Autowired
    private DetailsCreditRepository detailsCreditRepository;
    @GetMapping("/simulate")
    public String showSimulationForm(Model model) {
        model.addAttribute("simulation", new CreditSimulation());
        return "simulate";
    }

    @PostMapping("/simulate")
    public String simulateCredit(@ModelAttribute CreditSimulation simulation, Model model) {
        CreditSimulationResult result = creditSimulationService.calculateTotalAmount(simulation);
        model.addAttribute("simulationResult", result);
        return "simulationResult";
    }
}