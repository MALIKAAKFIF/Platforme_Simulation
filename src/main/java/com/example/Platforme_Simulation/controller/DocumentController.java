package com.example.Platforme_Simulation.controller;

import com.example.Platforme_Simulation.entity.DocumentSupport;
import com.example.Platforme_Simulation.repository.DocumentSupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/documents-support")
public class DocumentController {
    private final DocumentSupportRepository documentSupportRepository;

    @Autowired
    public DocumentController(DocumentSupportRepository documentSupportRepository) {
        this.documentSupportRepository = documentSupportRepository;
    }
    @GetMapping("/download")
    public ModelAndView downloadDocumentView() {
        return new ModelAndView("download");
    }





    @GetMapping("/download/{documentId}/{documentType}")
    public ResponseEntity<UrlResource> downloadDocument(@PathVariable Long documentId,
                                                        @PathVariable String documentType) {


        DocumentSupport documentSupport = documentSupportRepository.findByDocumentId(documentId);

        if (documentSupport == null) {
            // Gérer le cas où le document n'est pas trouvé
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
