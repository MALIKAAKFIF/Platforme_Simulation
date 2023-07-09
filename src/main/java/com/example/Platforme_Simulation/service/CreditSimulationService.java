package com.example.Platforme_Simulation.service;


import com.example.Platforme_Simulation.dto.CreditSimulationResult;
import com.example.Platforme_Simulation.entity.CreditSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditSimulationService {


    public CreditSimulationResult calculateTotalAmount(CreditSimulation simulation) {
        // la logique pour déterminer les facteurs basés sur l'emploi de l'utilisateur
        double employmentFactor = 1.0;
        if (simulation.getEmploie().equals("Fonctionnaire")) {
            employmentFactor = 6.25;
        } else if (simulation.getEmploie().equals("Retraites")) {
            employmentFactor = 8.75;
        } else if (simulation.getEmploie().equals("Salaries")) {
            employmentFactor = 9.25;
        }else if (simulation.getEmploie().equals("GE")) {
            employmentFactor = 8.25;
        }else if (simulation.getEmploie().equals("Professionnels")) {
            employmentFactor = 10.75;
        }

        // Calcul du montant du prêt en utilisant le facteur d'emploi
        double loanAmount = simulation.getCreditAmount() * employmentFactor;

        // Calcul de la mensualité
        double interestRate = 0.05; // Taux d'intérêt annuel de 5%
        double monthlyInterestRate = interestRate / 12.0;
        int totalMonths = simulation.getDuration() * 12;

        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));
        //DecimalFormat decimalFormat = new DecimalFormat("#0.00");
       // String formattedMonthlyPayment = decimalFormat.format(monthlyPayment);

        CreditSimulationResult result = new CreditSimulationResult();
        result.setCreditAmount(simulation.getCreditAmount());
        result.setDuration(simulation.getDuration());
        result.setEmploie(simulation.getEmploie());
        result.setMonthlyPayment(monthlyPayment);
        return result;
    }


}
