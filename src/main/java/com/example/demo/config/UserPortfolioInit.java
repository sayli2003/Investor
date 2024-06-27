package com.example.demo.config;

import com.example.demo.model.Wish;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserPortfolioInit {

    @Bean
    CommandLineRunner initializewishDatabase(WishRepository wishRepository) {
        return args -> {
            // Create a list of sample users
            var items = List.of(
                    new Wish((long) 1,"AAPL",35),
                    new Wish((long) 1,"GOOGL",24),
                    new Wish((long) 1,"IBM",44),
                    new Wish((long) 2,"AAPL",24),
                    new Wish((long) 2,"IBM",25),
                    new Wish((long) 3,"AAPL",35)


            );
            // Save all users to the database
            wishRepository.saveAll(items);
        };
    }
}
