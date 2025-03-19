package com.example.contacts_organiser.service;

import com.example.contacts_organiser.repository.ContactRepository;


public class InitContactService extends DefaultContactService{

    private ContactRepository repository;

    public InitContactService(ContactRepository repository) {
        super(repository);
        this.repository = repository;
        repository.loadContacts();
//        super(repository);
    }


//    public void postConstruct() {
//
//    }
}
