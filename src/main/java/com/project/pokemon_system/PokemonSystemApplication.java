package com.project.pokemon_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokemonSystemApplication {

    @Bean
    public ModelMapper modelMapper() {

        // tell Spring IoC to manage this model mapper object
        // to do that, annotate the method modelMapper() with @Bean
        // then, inject ModelMapper class object into PokemonServiceImpl class and call its method?
        return new ModelMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(PokemonSystemApplication.class, args);
    }

}
