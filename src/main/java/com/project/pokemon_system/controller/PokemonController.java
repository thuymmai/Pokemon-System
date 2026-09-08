package com.project.pokemon_system.controller;

import com.project.pokemon_system.dto.PokemonDto;
import com.project.pokemon_system.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // this annotation marks PokemonController class as a Spring MVC REST controller
@RequestMapping("api/pokemons") // defines the base URL for all REST APIs within this class
@AllArgsConstructor
public class PokemonController {

    // PokemonController requires PokemonService as a dependency
    // create an instance variable of PokemonService
    private PokemonService pokemonService;

    // build Add Pokemon REST API
    // use ResponseEntity class to construct the response of the REST API
    // pass PokemonDto as a type
    // name this method addPokemon
    @PostMapping // annotate addPokemon method with @PostMapping to map incoming HTTP POST request
    public ResponseEntity<PokemonDto> addPokemon(@RequestBody PokemonDto pokemonDto) {

        // @RequestBody is for extracting JSON object from the HTTP request and
        // convert that JSON into PokemonDto class object

       PokemonDto savedPokemon = pokemonService.addPokemon(pokemonDto);

       return new ResponseEntity<>(savedPokemon, HttpStatus.CREATED);
    }
}
