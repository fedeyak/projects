package com.example.contacts_organiser.service;

public interface ContactService {
    void showContacts();

    void addContact(String rawContact);

    void deleteContacts(String email);

    void saveContacts();

    void loadContacts();
}
