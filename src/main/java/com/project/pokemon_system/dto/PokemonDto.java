package com.project.pokemon_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {

    //define instance variables
    //the same as the ones in entity/Pokemon.java
    private Long id;
    private String name;
    private String description;
    private boolean final_revolution;
}
