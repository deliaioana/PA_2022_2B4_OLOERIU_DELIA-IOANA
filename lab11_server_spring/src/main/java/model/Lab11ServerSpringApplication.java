package model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab11ServerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab11ServerSpringApplication.class, args);
		Server server = new Server(6666);
	}
}
