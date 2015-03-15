package com.slgerkamp.introductory.spring.boot.springsession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.slgerkamp.introductory.spring.boot.springsession.domain.account.Account;
import com.slgerkamp.introductory.spring.boot.springsession.domain.account.AccountRepository;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.Bookmark;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.BookmarkRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
