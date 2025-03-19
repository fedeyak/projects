package contact_organiser.controller;

import contact_organiser.model.Contact;
import contact_organiser.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String showContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());

        return "index";
    }

    @GetMapping("/new")
    public String showCreateContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    @PostMapping()
    public String createContact(@ModelAttribute Contact contact) {
        Long id = System.currentTimeMillis();
        contactService.saveContact(contact);
        return "redirect:/contacts/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateContactForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.findContactById(id);
        if (contact == null) {
            return "redirect:/contacts/";
        }
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    @PostMapping("/update/{id}")
    public String updateContact(@ModelAttribute Contact contact) {
        contactService.updateContact(contact);
        return "redirect:/contacts/";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContactByID(id);
        return "redirect:/contacts/";
    }
}
