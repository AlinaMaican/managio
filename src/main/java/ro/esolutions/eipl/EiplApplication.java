package ro.esolutions.eipl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.esolutions.eipl.repositories.UserRepository;

@SpringBootApplication
public class EiplApplication {

	public static void main(String[] args) {
		SpringApplication.run(EiplApplication.class, args);
	}
}
