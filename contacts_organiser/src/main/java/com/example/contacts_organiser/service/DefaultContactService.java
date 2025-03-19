package com.example.contacts_organiser.service;

import com.example.contacts_organiser.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class DefaultContactService implements ContactService {

//    @Autowired
    private final ContactRepository repository;

    public DefaultContactService(ContactRepository repository) {
        this.repository = repository;
    }

//    public ContactService_apprentice() {
//        repository = new ContactRepository();
//    }

    @Override
    public void showContacts() {
        System.out.println("Showing contacts");
        repository.showContacts();
    }

    @Override
    public void addContact(String rawContact) {
        System.out.println("Adding contacts");
        repository.addContact(rawContact);
    }

    @Override
    public void deleteContacts(String email) {
        System.out.println("Deleting contacts");
        repository.deleteContacts(email);
    }

    @Override
    public void saveContacts() {
        System.out.println("Saving contacts");
        repository.saveContacts();
    }

    @Override
    public void loadContacts() {
        System.out.println("Loading contacts");
        repository.loadContacts();
    }

}
