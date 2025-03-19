package com.example.contacts_organiser.ui;

import com.example.contacts_organiser.service.ContactService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Application {

//    @Autowired
    private final ContactService contactService;


    Scanner scanner = new Scanner(System.in);

    public Application(ContactService contactService) {
        this.contactService = contactService;
    }

//    public Application() {
//        contactService = new ContactService_apprentice();
//    }

    @PostConstruct
    public void inti() {run();}


    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Please choose: " +
                    "[1] Show contacts, " +
                    "[2] Add contact, " +
                    "[3] Delete contact, " +
                    "[4] Save contacts, " +
                    "[5] Load contacts, " +
                    "[0] Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "0" -> {
                    exitProgram();
                    isRunning = false;
                }
                case "1" -> showContacts();
                case "2" -> addContact();
                case "3" -> deleteContacts();
                case "4" -> saveContacts();
                case "5" -> loadContacts();
                default -> wrongChoice();
            }
        }
    }

    private void exitProgram() {
        System.out.println("See you later");
    }

    private void wrongChoice() {
        System.out.println("Try again");
    }

    private void showContacts() {
        contactService.showContacts();
    }

    private void addContact() {
        System.out.println("Please add new contact in the format: name;phone;email");
        String rawContact = scanner.nextLine();
        contactService.addContact(rawContact);
    }

    private void deleteContacts() {
        System.out.println("Please enter the email");
        String email = scanner.nextLine();
        contactService.deleteContacts(email);
    }

    private void saveContacts() {
        contactService.saveContacts();
    }

    private void loadContacts() {
        contactService.loadContacts();
    }




}
