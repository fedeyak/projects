package com.example.contacts_organiser.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com/example/contacts_organiser")
@PropertySource("classpath:application.properties")
public class ContactsConfig {
}
