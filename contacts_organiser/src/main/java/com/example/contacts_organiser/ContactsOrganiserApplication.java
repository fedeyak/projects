package com.example.contacts_organiser;

import com.example.contacts_organiser.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsOrganiserApplication {

	public static void main(String[] args) {
//		Application application = new Application();
//		application.run();
		SpringApplication.run(ContactsOrganiserApplication.class, args);
	}

}
