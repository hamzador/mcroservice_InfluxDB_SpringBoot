package org.anl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AnalyzerServiceApplication implements CommandLineRunner{
	
	
	@Value("${database.url}")
	private String UrlDB;
	@Value("$(database.name)")
	private String nameDB;
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(AnalyzerServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		
	}

}
