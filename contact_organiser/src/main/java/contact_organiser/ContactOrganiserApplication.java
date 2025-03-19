package contact_organiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class ContactOrganiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactOrganiserApplication.class, args);
	}

}
