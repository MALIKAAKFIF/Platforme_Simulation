package com.example.Platforme_Simulation.service;

import com.example.Platforme_Simulation.dto.DemandeCreditDTO;
import com.example.Platforme_Simulation.dto.DossierDTO;
import com.example.Platforme_Simulation.dto.InterfaceAdmineDTO;
import com.example.Platforme_Simulation.entity.*;
import com.example.Platforme_Simulation.repository.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstructionService {
@Autowired
    private DemandeCreditRepository demandeCreditRepository;
@Autowired
    private DetailsCreditRepository detailsCreditRepository;
@Autowired
    private DocumentSupportRepository documentSupportRepository;
@Autowired
    private UtilisateurRepository utilisateurRepository;
@Autowired
private DossierRepository dossierRepository;

@Autowired
private AdresseRepository adresseRepository;

    @PersistenceContext
    private EntityManager entityManager;
    public Dossier saveDossier(DossierDTO dossierDTO) {
        Dossier dossier = convertDTOToEntity(dossierDTO);
        return dossierRepository.save(dossier);
    }
    private Dossier convertDTOToEntity(DossierDTO dossierDTO) {
        Dossier dossier = new Dossier();
        DemandeCredit demandeCredit=new DemandeCredit();
        dossier.setStatus("En attente de traitement");
        return dossier;
    }



    public Dossier saveDossierWithIDs(DemandeCredit demandeCredit, DetailsCredit detailsCredit, DocumentSupport documentSupport, Utilisateur utilisateur, CreditSimulation creditSimulation, Adresse adresse) {



       utilisateur=utilisateurRepository.findByRadical(utilisateur.getRadical());

        utilisateur.getDemandesCredit().add(demandeCredit);

        utilisateurRepository.saveAndFlush(utilisateur);


        demandeCredit.setUtilisateur(utilisateur);

        demandeCreditRepository.saveAndFlush(demandeCredit);

        detailsCredit.setDemandeCredit(demandeCredit);

        detailsCreditRepository.saveAndFlush(detailsCredit);


        adresse.setUtilisateur(utilisateur);
       adresseRepository.saveAndFlush(adresse);


       documentSupport.setDemandeCredit(demandeCredit);

       documentSupportRepository.saveAndFlush(documentSupport);

       Dossier dossier=new Dossier();
       dossier.setUtilisateur(utilisateur);
       dossier.setDemandeCredit(demandeCredit);
       dossier.setDetailsCredit(detailsCredit);
       dossier.setDocumentSupport(documentSupport);
       dossier.setAdresse(adresse);

        Dossier savedDossier = dossierRepository.saveAndFlush(dossier);
        return savedDossier;



    }
    public void saveDocument(MultipartFile file, DocumentSupport documentSupport) throws IOException {


        documentSupportRepository.save(documentSupport);
    }

    public String saveDocumentToServer(MultipartFile file, String fileName) throws IOException {
        // Définissez le répertoire de stockage sur le serveur
        String storageDirectory = "./documents";

        // Construisez le chemin complet du fichier
        String filePath = storageDirectory + "/" + fileName;

        // Enregistrez le fichier sur le serveur
        file.transferTo(new File(filePath));

        return filePath;
    }
    public String generateUniqueFileName(String originalFileName) {
        String baseName = FilenameUtils.getBaseName(originalFileName);
        String extension = FilenameUtils.getExtension(originalFileName);

        // Générez un nom de fichier unique en ajoutant un timestamp à la base du nom de fichier
        String uniqueFileName = baseName + "_" + System.currentTimeMillis() + "." + extension;

        return uniqueFileName;
    }

    public Utilisateur getUtlisateurByEmail(String email) {
        return utilisateurRepository.findUtilisateurIdByEmail(email);
    }



}
