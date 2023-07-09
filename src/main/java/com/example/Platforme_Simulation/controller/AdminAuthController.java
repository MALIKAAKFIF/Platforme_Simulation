package com.example.Platforme_Simulation.controller;


import com.example.Platforme_Simulation.dto.AdminDTO;
import com.example.Platforme_Simulation.entity.*;

import com.example.Platforme_Simulation.repository.DocumentSupportRepository;
import com.example.Platforme_Simulation.repository.DossierRepository;
import com.example.Platforme_Simulation.service.AdminService;

import com.example.Platforme_Simulation.service.DemandeCreditService;
import com.example.Platforme_Simulation.service.NotificationService;
import com.example.Platforme_Simulation.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.nio.file.Path;
import java.time.ZoneId;
import java.util.*;

@Controller
public class AdminAuthController {

    private AdminService adminService;
    private AdminServiceImpl adminServiceImpl;

    private DossierRepository dossierRepository;
    public AdminAuthController(AdminService adminService) {
        this.adminService = adminService;
    }
    @Autowired
    private DemandeCreditService demandeCreditService;
    @Autowired
    private DocumentSupportRepository documentSupportRepository;

     DocumentController documentController;
@Autowired
private NotificationService notificationService;
@Autowired
private SimpMessagingTemplate messagingTemplate;



    @GetMapping("/adminLogin")
    public String loginForm() {
        return "adminLogin";
    }
    @PostMapping("/adminLogin")
    public String login(@RequestParam("matricule") String matricule,
                        @RequestParam("password") String password,
                        Model model) {
        Admin admin = adminService.findByMatricule(matricule);

        if (admin != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, admin.getPassword())) {
                model.addAttribute("matricule", matricule); // Ajouter le matricule à l'objet Model
                return "redirect:/dossiers?matricule=" + matricule;
            }
        }

        model.addAttribute("errorMessage", "Identifiants invalides");
        model.addAttribute("showErrorMessage", true);
        return "adminLogin";
    }


    @GetMapping("/adminRegister")
    public String showRegistrationForm(Model model) {
        AdminDTO adminDTO = new AdminDTO();
        model.addAttribute("admin", adminDTO);
        return "adminRegister";
    }

    @PostMapping("/adminRegister/save")
    public String registration(@RequestParam("matricule") String matricule,
                               @RequestParam("password") String password,
                               @RequestParam("nom") String nom,
                               @RequestParam("prenom") String prenom,
                               Model model) {
        Admin existing = adminService.findByMatricule(matricule);
        if (existing != null) {
            existing.setPassword(password);
            existing.setNom(nom);
            existing.setPrenom(prenom);
            adminService.saveAdmin(existing);
            return "redirect:/adminLogin?success";

        } else {
            model.addAttribute("errorMessage", "Ce matricule n'existe pas. Veuillez contacter le superadmin pour obtenir un matricule valide.");
            return "adminRegister";
        }
    }



    @GetMapping("/dossiers")
    public String getRadicauxByMatricule(HttpServletRequest request, HttpSession session, Model model,String type) {
        String matricule = request.getParameter("matricule");
        session.setAttribute("matricule", matricule);
        List<String> radicaux = adminService.getRadicauxByMatricule(matricule);
        List<Utilisateur> utilisateurs = adminService.findUtilisateursByRadicaux(radicaux);

        for (Utilisateur utilisateur : utilisateurs) {
            Long utilisateurId = utilisateur.getIdUtilisateur();
            Adresse adresse = adminService.getAdresseByUtilisateurId(utilisateurId);
            List<Dossier> dossiers = adminService.getDossiersByUtilisateurId(utilisateurId);
            List<DemandeCredit> demandeCredits = adminService.getDemandeByUtilisateurId(utilisateurId);

            for (DemandeCredit demandeCredit : demandeCredits) {
                Long demandeId = demandeCredit.getID_demande();
                DetailsCredit detailsCredit = adminService.getDetailsCreditByDemandeCreditId(demandeId);
                demandeCredit.setDetailsCredit(detailsCredit);


            }

            utilisateur.setAdresse(adresse);
            utilisateur.setDossiers(dossiers);
            utilisateur.setDemandeCredits(demandeCredits);
        }

        model.addAttribute("radicaux", radicaux);
        model.addAttribute("matricule", matricule);
        model.addAttribute("utilisateurs", utilisateurs);


        return "dossiers";
    }

    @PostMapping("/dossiers/{id}/statut")
    public String updateDossierStatus(@PathVariable Long id, @RequestParam("statut") String newStatus,HttpSession session, Model model) throws IOException {
        adminService.updateDossierStatus(id, newStatus);
        Long demandeCreditId = id;
        Long dossierId = adminService.getDossierIdByDemandeCreditId(demandeCreditId);

        String message = "Le statut de votre demande est : " + newStatus;
        Date date = new Date();
        Date utilDate = new Date();
        java.sql.Date sqlDate = java.sql.Date.valueOf(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());


        // Créer une nouvelle instance de Notification
        Notification notification = new Notification();
        notification.setIdDossier(Math.toIntExact(dossierId));
        notification.setMessage(message);
        notification.setDateNotification(sqlDate);
        notification.setLue(false);

        // Appeler la méthode de service pour créer la notification
        notificationService.createNotification(notification);
        messagingTemplate.convertAndSend("/topic/notifications", notification);



        String matricule = (String) session.getAttribute("matricule");
        List<String> radicaux = adminService.getRadicauxByMatricule(matricule);
        List<Utilisateur> utilisateurs = adminService.findUtilisateursByRadicaux(radicaux);

        for (Utilisateur utilisateur : utilisateurs) {
            Long utilisateurId = utilisateur.getIdUtilisateur();
            Adresse adresse = adminService.getAdresseByUtilisateurId(utilisateurId);
            List<Dossier> dossiers = adminService.getDossiersByUtilisateurId(utilisateurId);
            List<DemandeCredit> demandeCredits = adminService.getDemandeByUtilisateurId(utilisateurId);

            for (DemandeCredit demandeCredit : demandeCredits) {
                Long demandeId = demandeCredit.getID_demande();
                DetailsCredit detailsCredit = adminService.getDetailsCreditByDemandeCreditId(demandeId);
                demandeCredit.setDetailsCredit(detailsCredit);
            }

            utilisateur.setAdresse(adresse);
            utilisateur.setDossiers(dossiers);
            utilisateur.setDemandeCredits(demandeCredits);
        }

        model.addAttribute("radicaux", radicaux);
        model.addAttribute("matricule", matricule);
        model.addAttribute("utilisateurs", utilisateurs);
        return "dossiers";
    }


    //@GetMapping("/dossiers/{id}/statut")
    //public String showStatutDossier(@PathVariable Long id, Model model) {
        //String statut = adminService.getStatutDossier(id);
        //model.addAttribute("statut", statut);
        //model.addAttribute("demandeId", id); // Ajouter la demandeId à l'objet Model

        //return "statutDemande";
    //}

    @GetMapping("/dossiers/{id}/notifications")
    @ResponseBody
    public List<Notification> getNotificationsByDossierId(@PathVariable Long id) {
        List<Notification> notification = notificationService.getNotificationsByDossierId(Collections.singletonList(id));
        return notification;
    }


    @GetMapping("/download/{documentId}/{documentType}")
    public ResponseEntity<UrlResource> downloadDocument(@PathVariable Long documentId,
                                                        @PathVariable String documentType) {


        DocumentSupport documentSupport = documentSupportRepository.findByDocumentId(documentId);

        if (documentSupport == null) {
            return ResponseEntity.notFound().build();
        } else {
            String documentPath;
            if (documentType.equals("attestation-salaire")) {
                documentPath = documentSupport.getCheminDocumentAttestationSalaire();
            } else if (documentType.equals("bulletin-paie")) {
                documentPath = documentSupport.getCheminDocumentBulletinPaie();
            } else {
                // Gérer le cas où le type de document n'est pas valide
                return ResponseEntity.badRequest().build();
            }

            if (documentPath == null) {
                // Gérer le cas où le chemin du document est null
                return ResponseEntity.notFound().build();
            }

            Path file = Path.of(documentPath);
            UrlResource resource;
            try {
                resource = new UrlResource(file.toUri());
            } catch (IOException e) {
                // Gérer les erreurs de chargement du fichier
                return ResponseEntity.badRequest().build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        }
    }

}

