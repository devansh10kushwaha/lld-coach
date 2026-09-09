package com.lldcoach.config;

import com.lldcoach.domain.problem.Problem;
import com.lldcoach.repository.ProblemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadProblems(ProblemRepository repository) {

        return args -> {

            if (repository.count() == 0) {

                repository.save(new Problem(
                        "Parking Lot",
                        "Design a parking lot system that supports different vehicle types, parking spots, tickets and fee calculation.",
                        "Medium"
                ));

                repository.save(new Problem(
                        "Elevator System",
                        "Design an elevator system that handles multiple elevators, floor requests and elevator movement.",
                        "Medium"
                ));

                repository.save(new Problem(
                        "Vending Machine",
                        "Design a vending machine supporting products, payments, inventory and change calculation.",
                        "Easy"
                ));
            }
        };
    }
}