package contact_organiser.service;

import contact_organiser.model.Contact;
import java.util.List;

public interface ContactService {

    public List<Contact> getAllContacts();
    public Contact findContactById(Long id);
    public void saveContact(Contact contact);
    public void updateContact(Contact contact);
    public void showContact();
    public void deleteContactByID(Long id);
}
