package com.project.pokemon_system.controller;

import com.project.pokemon_system.dto.PokemonDto;
import com.project.pokemon_system.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // build Get Pokemon REST API
    @GetMapping("{id}") // this is a URI template variable, bind the value of this URI to the getPokemon() method parameter
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable("id") Long pokemonId) {
        PokemonDto pokemonDto = pokemonService.getPokemon(pokemonId);
        return new ResponseEntity<>(pokemonDto, HttpStatus.OK);
    }

    // build Get All Pokemons REST API
    @GetMapping
    public ResponseEntity<List<PokemonDto>> getAllPokemons() {
        List<PokemonDto> pokemons = pokemonService.getAllPokemons();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    // build Update Pokemon REST API
    @PutMapping("{id}")

    /* @RequestBody extracts the updated JSON from the HTTP request and it will
    *  convert that JSON into PokemonDto Java object
    *  @PutMapping updated an entire exsting source, such as all fields here: name, description, and final evolution fields */
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") Long pokemonId) {
        PokemonDto updatedPokemon = pokemonService.updatePokemon(pokemonDto, pokemonId);
        return ResponseEntity.ok(updatedPokemon);
    }

    // build Delete Pokemon REST API
    // use @DeleteMapping to map incoming HTTP DELETE request to the deletePokemon() method
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") Long pokemonId) {
        pokemonService.deletePokemon(pokemonId);
        return ResponseEntity.ok("Pokemon deleted successfully!");
    }

    // build Final Evolution Pokemon REST API (yes it is a Final Evolution)
    // use @PatchMapping to map incoming HTTP patch request to this particular method.
    // @PatchMapping is for partially updating existing source. For ex, I only update the Final Evolution field
    @PatchMapping("{id}/final-evolution")
    public ResponseEntity<PokemonDto> isFinalEvolution(@PathVariable("id") Long pokemonId) {
        PokemonDto updatedPokemon = pokemonService.isFinalEvolution(pokemonId);
        return ResponseEntity.ok(updatedPokemon);
    }

    // build a isNotFinalEvolution Pokemon REST API (no, it is not in its Final Evolution form)
    // @PatchMapping for
    @PatchMapping("{id}/not-final-evolution")
    public ResponseEntity<PokemonDto> isNotFinalEvolution(@PathVariable("id") Long pokemonId) {
        PokemonDto updatedPokemon = pokemonService.isNotFinalEvolution(pokemonId);
        return ResponseEntity.ok(updatedPokemon);
    }
}
