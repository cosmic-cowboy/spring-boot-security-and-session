package com.slgerkamp.introductory.spring.boot.springsession;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.slgerkamp.introductory.spring.boot.springsession.domain.account.Account;
import com.slgerkamp.introductory.spring.boot.springsession.domain.account.AccountRepository;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.Bookmark;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.BookmarkRepository;


@SpringBootApplication
public class Application {

    @Bean
    CommandLineRunner init(AccountRepository accountRepository,
                           BookmarkRepository bookmarkRepository) {
        return (evt) -> Stream.of("user01", "user02", "user03")
                .forEach(a -> {
                    Account account = accountRepository.save(new Account(a,
                            "password"));
                    bookmarkRepository.save(new Bookmark(account,
                            "http://bookmark.com/1/" + a, "A description"));
                    bookmarkRepository.save(new Bookmark(account,
                            "http://bookmark.com/2/" + a, "A description"));
                });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}




