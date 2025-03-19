package com.example.contacts_organiser.parcer;

import com.example.contacts_organiser.exceptions.WrongStringFormatException;
import com.example.contacts_organiser.model.Contact;

public interface ContactsParser {

    Contact stringToContact(String string) throws WrongStringFormatException;

}
