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
public class YouTunesApplication implements ApplicationRunner{

	@Autowired
	private CustomerRepository postgresDb;
	public static void main(String[] args) {
		SpringApplication.run(YouTunesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(postgresDb.getCountryWithMosyCustomers());
		System.out.println(postgresDb.getMostPopularGenre());
	}

}
