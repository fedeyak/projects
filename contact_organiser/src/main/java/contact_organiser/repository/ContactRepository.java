package contact_organiser.repository;

import contact_organiser.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> findAll();

    public Optional<Contact> findContactById(Long id);

    public Contact save(Contact contact) ;

    public Contact update(Contact contact) ;

    public void deleteContactByID(Long id) ;
}
