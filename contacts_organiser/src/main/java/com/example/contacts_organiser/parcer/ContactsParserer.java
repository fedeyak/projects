package com.example.contacts_organiser.parcer;

import com.example.contacts_organiser.exceptions.WrongStringFormatException;
import com.example.contacts_organiser.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactsParserer implements ContactsParser {

    @Override
    public Contact stringToContact(String string) throws WrongStringFormatException {
        String[] contactDetails = string.split(";");
        if (contactDetails.length != 3 ) {
            throw new WrongStringFormatException();
        } else {
            Contact contact = new Contact();
            contact.setName(contactDetails[0]);
            contact.setPhone(contactDetails[1]);
            contact.setEmail(contactDetails[2]);
            return contact;
        }
    }
}
