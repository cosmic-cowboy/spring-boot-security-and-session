package com.slgerkamp.introductory.spring.boot.formandbasicauth;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user.User;
import com.slgerkamp.introductory.spring.boot.formandbasicauth.domain.user.UserRepository;



@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (evt) -> Stream.of("user01", "user02", "user03")
                .forEach(a -> {
                    userRepository.save(
                    		new User(a, "$2a$10$tFfSjQksxXWSr9aQKInAieagRA0KpCxcLs5QyuY8VSiJ.6e93C3ey"));
                });
    }

	public static void main( String[] args ){
    	SpringApplication.run(Application.class, args);
    }
}
