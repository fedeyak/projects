package com.example.contacts_organiser.config;

import com.example.contacts_organiser.parcer.ContactsParser;
import com.example.contacts_organiser.parcer.ContactsParserer;
import com.example.contacts_organiser.repository.ContactRepository;
import com.example.contacts_organiser.service.ContactService;
import com.example.contacts_organiser.service.DefaultContactService;
import com.example.contacts_organiser.service.InitContactService;
import com.example.contacts_organiser.ui.Application;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Configuration
@Profile("init")
public class InitContactsConfig {

    @Bean
    public ContactsParser parser() {
        return new ContactsParserer();
    }

    @Bean
    public ContactRepository repository() {
//        ContactRepository repository = new ContactRepository(parser());
//        repository.loadContacts();
//        return repository;
        return new ContactRepository(parser());
    }

    @Bean
    public ContactService service() {
        return new InitContactService(repository());
    }

    @Bean
    public Application application() {
        return new Application(service());
    }
}
