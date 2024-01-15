package com.example.restapi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    2L, "Mariam", "mariam@gmail.com", LocalDate.of(2000,10,23)
            );
            Student nikita = new Student(
                    1L, "Nikita", "nikita@gmail.com", LocalDate.of(1999,10,29)
            );

            repository.saveAll(List.of(nikita, mariam));
        };

    }

}
