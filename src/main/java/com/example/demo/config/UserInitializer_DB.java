package com.example.demo.config;

//import com.example.demo.model.UserEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserInitializer_DB {

    @Bean
    CommandLineRunner initializeDatabase(UserRepository userRepository) {
        return args -> {
            // Create a list of sample users
            var items = List.of(
                    new UserEntity((long)1,(double)11200,"Sayli Borole","sayliborole29@gmail.com","abc"),
                    new UserEntity((long) 2, (double)14400,"Boopathy","boopathy@gmail.com","abc"),
                    new UserEntity((long) 3, (double)12200,"Parduman","Parduman@gmail.com","abc"),
                    new UserEntity((long) 4, (double)14400,"Radhika","radhika@gmail.com","abc"),
                    new UserEntity((long) 5, (double)14400,"swati","swati@gmail.com","abc")


            );
            // Save all users to the database
            userRepository.saveAll(items);
        };
    }
}
