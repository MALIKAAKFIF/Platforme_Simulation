package com.example.Platforme_Simulation.controller;



import com.example.Platforme_Simulation.entity.Contact;
import com.example.Platforme_Simulation.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contactForm";
    }

    @PostMapping
    public String processContactForm(Contact contact) {
        contactRepository.save(contact);
        return "contactSuccess";
    }
}
