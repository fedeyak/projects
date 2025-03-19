package com.example.contacts_organiser.repository;

import com.example.contacts_organiser.exceptions.WrongStringFormatException;
import com.example.contacts_organiser.model.Contact;
import com.example.contacts_organiser.parcer.ContactsParser;
import com.example.contacts_organiser.parcer.ContactsParserer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository {

    private final List<Contact> contacts;
    private final ContactsParser parser;

    @Value("${contacts.file.path}")
    private String filePath;

    private String filePth2 = "src/main/resources/contacts.txt";

    public ContactRepository(ContactsParser parser) {
        this.contacts = new ArrayList<>();
        this.parser = parser;
    }

    @PostConstruct
    public void init() {
        System.out.println("File path injected: " + filePath);
    }

    public void showContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void addContact(String rawContact) {
        try {
            Contact contact = parser.stringToContact(rawContact);
            String email = contact.getEmail();
            contacts.removeIf(c -> c.getEmail().equals(email));
            contacts.add(contact);
        } catch (WrongStringFormatException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public void deleteContacts(String email) {
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) {
                contacts.remove(contact);
                System.out.println("Contact removed");
                return;
            }
        }
        System.out.println("No such email found");
    }

    public void saveContacts() {
        System.out.println("Saving contacts to a file");
        File file = new File(filePath);
        boolean append = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + ";" + contact.getPhone() + ";" + contact.getEmail() + "\r");
//                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadContacts() {
        String contacts = null;
        try {
            contacts = Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] arrayOfRawContacts = contacts.split("\r");
        for (String rawContact : arrayOfRawContacts) {
            addContact(rawContact);
        }
    }
}
