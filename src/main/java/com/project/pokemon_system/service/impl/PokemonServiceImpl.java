package com.project.pokemon_system.service.impl;

import com.project.pokemon_system.dto.PokemonDto;
import com.project.pokemon_system.entity.Pokemon;
import com.project.pokemon_system.repository.PokemonRepository;
import com.project.pokemon_system.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor //automatically create a parameterized constructor for PokemonServiceImpl class
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Override
    public PokemonDto addPokemon(PokemonDto pokemonDto) {

        // convert PokemonDto into Pokemon JPA entity
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setDescription(pokemonDto.getDescription());
        pokemon.setFinal_revolution(pokemonDto.isFinal_revolution());

        // save Pokemon JPA entity in a database
        // this save() method return saved entity object
        // introduce local variable of type Pokemon and name it savedPokemon
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        // addPokemon return PokemonDto
        // so convert savedPokemon JPA entity object into PokemonDto object
        PokemonDto savedPokemonDto = new PokemonDto();
        savedPokemonDto.setId(savedPokemon.getId());
        savedPokemonDto.setName(savedPokemon.getName());
        savedPokemonDto.setDescription(savedPokemon.getDescription());
        savedPokemonDto.setFinal_revolution(savedPokemon.isFinal_revolution());

        return savedPokemonDto;
    }
}
