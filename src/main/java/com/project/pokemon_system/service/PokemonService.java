package com.project.pokemon_system.service;

import com.project.pokemon_system.dto.PokemonDto;

import java.util.List;

public interface PokemonService {

    // create PokemonDto method
    PokemonDto addPokemon(PokemonDto pokemonDto);

    PokemonDto getPokemon(Long id);

    // define Get All Pokemon method
    List<PokemonDto> getAllPokemons();

    // define Update Pokemon method
    PokemonDto updatePokemon(PokemonDto pokemonDto, Long id);

    // define Delete Pokemon method
    void deletePokemon(Long id);
}
