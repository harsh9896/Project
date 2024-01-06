package com.example.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				 registry.addMapping("/**")
//                 .allowedOrigins("*")
//                 .allowedHeaders("*")
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
//                 .maxAge(-1)   // add maxAge
//                 .allowCredentials(true);
//			}
//		};
//	}

}
