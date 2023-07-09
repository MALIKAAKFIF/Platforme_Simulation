package com.example.Platforme_Simulation.controller;

import com.example.Platforme_Simulation.dto.DossierDTO;
import com.example.Platforme_Simulation.entity.*;
import com.example.Platforme_Simulation.repository.DossierRepository;
import com.example.Platforme_Simulation.service.*;
import com.example.Platforme_Simulation.service.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.time.ZoneId;
import java.util.*;

@Controller
public class InstructionController {

    private final InstructionService instructionService;
   private final CreditSimulationService creditSimulationService;
   @Autowired
    UtilisateurService utilisateurService;
   @Autowired
    DossierRepository dossierRepository;
   @Autowired
    NotificationService notificationService;
   @Autowired
    DemandeCreditService demandeCreditService;
    @Autowired
    public InstructionController(InstructionService instructionService, CreditSimulationService creditSimulationService) {
        this.instructionService = instructionService;
        this.creditSimulationService = creditSimulationService;
    }

    @GetMapping("/instruction")
    public String showInstructionForm(Model model) {
        String email = getEmailFromSecurityContext();
        Utilisateur utilisateur = instructionService.getUtlisateurByEmail(email);
        boolean isDossierExists = dossierRepository.existsByUtilisateur(utilisateur);

        if (isDossierExists) {
            return "redirect:/vueClient";
        } else {
            model.addAttribute("dossierDTO", new DossierDTO());
            return "instruction";
        }
    }
    private String getEmailFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/instruction")
    public String saveDossierWithIDs(

            @RequestParam("attestation_salaire") MultipartFile attestationSalaire,
            @RequestParam("bulletin_paie_mois_dernier") MultipartFile bulletinPaieMoisDernier,
            @RequestParam("deuxieme_bulletin_paie_mois_dernier") MultipartFile deuxiemeBulletinPaieMoisDernier,
            @RequestParam("cin_recto") MultipartFile cinRecto,
            @RequestParam("cin_verso") MultipartFile cinVerso,
            @RequestParam("ville") String ville,

            @RequestParam("telephone") String telephone,
            @RequestParam("revenuMensuel") double revenuMensuel,
            @RequestParam("codePostal") int codePostal,
            @RequestParam("credimontant") double credit,
            @RequestParam("amount") String amount,
            @RequestParam("duration") String duration,
            @RequestParam("emploie") String emploie,
            @RequestParam("radical") String radical,
            @RequestParam("monthlyPayment") String monthlyPayment,Principal principal) throws IOException {

        String email = getEmailFromSecurityContext();
        Utilisateur client = instructionService.getUtlisateurByEmail(email);
        boolean isDossierExists = dossierRepository.existsByUtilisateur(client);

        if (isDossierExists) {
            return "redirect:/vueClient";
        }else {



        DocumentSupport documentSupport = new DocumentSupport();

        DemandeCredit demandeCredit = new DemandeCredit();
        amount=amount.replace(",", "");
        emploie= emploie.replace(",", "");
        duration=duration.replace(",", "");
        monthlyPayment=monthlyPayment.replace(",", "");


        Date date = new Date();
        Date utilDate = new Date();
        java.sql.Date sqlDate = java.sql.Date.valueOf(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        demandeCredit.setDateDemande(sqlDate);
        demandeCredit.setStatut("En cours");
        demandeCredit.setMontantDemande(Float.valueOf(amount));
        demandeCredit.setMontantCreditAutreEtabliCredit(credit);

        DetailsCredit detailsCredit = new DetailsCredit();
        detailsCredit.setTelephone(telephone);
        detailsCredit.setRevenuMensuel(revenuMensuel);
        detailsCredit.setDureeRemboursement(Integer.valueOf(duration));
        detailsCredit.setMontantTotalAvecInteret(Float.valueOf(monthlyPayment));
        detailsCredit.setEmploie(emploie);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRadical(radical);
        CreditSimulation creditSimulation = new CreditSimulation();
        Adresse adresseObj = new Adresse();
        adresseObj.setVille(ville);
        adresseObj.setCodePostal(codePostal);

       documentSupport.setDateUpload(sqlDate);

        String path = "C:\\AppWithNewDB\\Platforme_Simulation\\documents\\";



        if (!attestationSalaire.isEmpty()) {
            try {
                String fileName = instructionService.generateUniqueFileName(attestationSalaire.getOriginalFilename());

                String filePath = path + fileName;
                FileUtil.createDirectoryIfNotExists(path);
                FileUtil.saveFile(attestationSalaire, path, fileName);
                documentSupport.setCheminDocumentAttestationSalaire(filePath);
            } catch (IOException e) {
                // Gérer l'erreur de traitement du fichier
            }
        }

        if (!bulletinPaieMoisDernier.isEmpty()) {
            try {
                String fileName = instructionService.generateUniqueFileName(bulletinPaieMoisDernier.getOriginalFilename());

                String filePath = path + fileName;
                FileUtil.createDirectoryIfNotExists(path);
                FileUtil.saveFile(bulletinPaieMoisDernier, path, fileName);
                documentSupport.setCheminDocumentBulletinPaie(filePath);
            } catch (IOException e) {
                // Gérer l'erreur de traitement du fichier
            }
        }

        if (!deuxiemeBulletinPaieMoisDernier.isEmpty()) {
            try {
                String fileName = instructionService.generateUniqueFileName(deuxiemeBulletinPaieMoisDernier.getOriginalFilename());
                String filePath = path + fileName;
                FileUtil.createDirectoryIfNotExists(path);
                FileUtil.saveFile(deuxiemeBulletinPaieMoisDernier, path, fileName);
                documentSupport.setCheminDocumentDeuxiemeBulletinPaie(filePath);
            } catch (IOException e) {
                // Gérer l'erreur de traitement du fichier
            }
        }

        if (!cinRecto.isEmpty()) {
            try {
                String fileName = instructionService.generateUniqueFileName(cinRecto.getOriginalFilename());
                String filePath = path + fileName;
                FileUtil.createDirectoryIfNotExists(path);
                FileUtil.saveFile(cinRecto, path, fileName);
                documentSupport.setCheminDocumentCinRecto(filePath);
            } catch (IOException e) {
                // Gérer l'erreur de traitement du fichier
            }
        }


        if (!cinVerso.isEmpty()) {
            try {
                String fileName = instructionService.generateUniqueFileName(cinVerso.getOriginalFilename());
                String filePath = path + fileName;
                FileUtil.createDirectoryIfNotExists(path);
                FileUtil.saveFile(cinVerso, path, fileName);
                documentSupport.setCheminDocumentCinVerso(filePath);
            } catch (IOException e) {
                // Gérer l'erreur de traitement du fichier
            }
        }


        instructionService.saveDossierWithIDs(demandeCredit, detailsCredit, documentSupport, utilisateur, creditSimulation,adresseObj);

        return "instructionResult";}
    }
    @GetMapping("/vueClient")
    public String showVueClient(Model model) {
        String email = getEmailFromSecurityContext();
        Utilisateur utilisateur = instructionService.getUtlisateurByEmail(email);
        List<Long> dossierIds = dossierRepository.findIdByUtilisateurId(utilisateur.getIdUtilisateur());

        List<Notification> notifications = new ArrayList<>();
        for (Long dossierId : dossierIds) {
            List<Notification> dossierNotifications = notificationService.getNotificationsByDossierId(Collections.singletonList(dossierId));
            if (!dossierNotifications.isEmpty()) {
                dossierNotifications.sort(Comparator.comparing(Notification::getDateNotification).reversed());
                notifications.add(dossierNotifications.get(0));
            }
        }

        Utilisateur user = instructionService.getUtlisateurByEmail(email);
        DemandeCredit derniereDemande = demandeCreditService.findLatestDemandeCreditByUtilisateur(user.getIdUtilisateur());

        model.addAttribute("notifications", notifications);
        model.addAttribute("montantDemande", derniereDemande.getMontantDemande());
        model.addAttribute("duree", derniereDemande.getDetailsCredit().getDureeRemboursement());
        model.addAttribute("emploi", derniereDemande.getDetailsCredit().getEmploie());
        model.addAttribute("mensualite", derniereDemande.getDetailsCredit().getMontantTotalAvecInteret());;

        return "vueClient";
    }

}


