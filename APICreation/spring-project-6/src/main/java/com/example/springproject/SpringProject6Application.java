package com.example.springproject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@CrossOrigin(origins = "*")
@SpringBootApplication
//@EnableWebSecurity
public class SpringProject6Application {

	public static void main(String[] args) {
		try {
		System.getProperties().put( "server.port", 8085);
		SpringApplication.run(SpringProject6Application.class, args);
	}
		catch (Exception e) {
		System.out.println("Nikhil m::"+e.getMessage());
		}
	}
//	@Bean
//	NoOpPasswordEncoder pwdEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
}
