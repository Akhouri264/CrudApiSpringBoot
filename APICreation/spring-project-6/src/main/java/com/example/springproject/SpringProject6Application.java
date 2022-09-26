package com.example.springproject;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.springproject.controllers.libraryController;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableSwagger2
//@ComponentScan(basePackageClasses = libraryController.class)
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
	@Bean
  public Docket api() {

		String val="com.example.springproject.controllers";
      Set<String> protocolSet = new HashSet<String>();
      protocolSet.add("http");
      protocolSet.add("https");
      return new Docket(DocumentationType.SWAGGER_2)
      		.select()
      		.apis(RequestHandlerSelectors.
      				basePackage(val)
      				).paths((PathSelectors.regex("/error.*")).negate()).build().produces(protocolSet);
  }
}
