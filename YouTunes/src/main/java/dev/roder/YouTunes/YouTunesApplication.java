package dev.roder.YouTunes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.roder.YouTunes.models.Customer;
import dev.roder.YouTunes.models.CustomerCountry;
import dev.roder.YouTunes.repositories.CustomerRepository;

@SpringBootApplication
public class YouTunesApplication {

	@Autowired
	private CustomerRepository postgresDb;

	public static void main(String[] args) {
		SpringApplication.run(YouTunesApplication.class, args);
	}
}
