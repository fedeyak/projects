package contact_organiser.service;

import contact_organiser.model.Contact;
import contact_organiser.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepository.findContactById(id).orElse(null);
    }

    @Override
    public void saveContact(Contact contact) {
        contact.setId(System.currentTimeMillis());
        contactRepository.save(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactRepository.update(contact);
    }

    @Override
    public void showContact() {
    }

    public void deleteContactByID(Long id) {
        contactRepository.deleteContactByID(id);
    }
}
