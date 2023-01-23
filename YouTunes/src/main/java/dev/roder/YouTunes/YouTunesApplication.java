package dev.roder.YouTunes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.roder.YouTunes.access.YouTunesDAO;

@SpringBootApplication
public class YouTunesApplication implements ApplicationRunner{

	@Autowired
	private YouTunesDAO postgresDb;
	public static void main(String[] args) {
		SpringApplication.run(YouTunesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		postgresDb.test();
	}

}
