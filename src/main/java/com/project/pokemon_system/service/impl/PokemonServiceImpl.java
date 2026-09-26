package com.project.pokemon_system.service.impl;

import com.project.pokemon_system.dto.PokemonDto;
import com.project.pokemon_system.entity.Pokemon;
import com.project.pokemon_system.exception.ResourceNotFoundException;
import com.project.pokemon_system.repository.PokemonRepository;
import com.project.pokemon_system.service.PokemonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor //automatically create a parameterized constructor for PokemonServiceImpl class using PokemonRepository and ModelMapper fields
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    // inject ModelMapper object (from the main method)
    private ModelMapper modelMapper;

    @Override
    public PokemonDto addPokemon(PokemonDto pokemonDto) {

        // use ModelMapper to convert PokemonDto into Pokemon JPA entity (code commented out below)
//        Pokemon pokemon = new Pokemon();
//        pokemon.setName(pokemonDto.getName());
//        pokemon.setDescription(pokemonDto.getDescription());
//        pokemon.setFinal_revolution(pokemonDto.isFinal_revolution());

        Pokemon pokemon = modelMapper.map(pokemonDto, Pokemon.class);

        /***************************************************************************************************/

        // save Pokemon JPA entity in a database
        // this save() method return saved entity object
        // introduce local variable of type Pokemon and name it savedPokemon
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        /***************************************************************************************************/

        // addPokemon return PokemonDto
        // so convert savedPokemon JPA entity object into PokemonDto object
        // use ModelMapper again
//        PokemonDto savedPokemonDto = new PokemonDto();
//        savedPokemonDto.setId(savedPokemon.getId());
//        savedPokemonDto.setName(savedPokemon.getName());
//        savedPokemonDto.setDescription(savedPokemon.getDescription());
//        savedPokemonDto.setFinal_revolution(savedPokemon.isFinal_revolution());

        PokemonDto savedPokemonDto = modelMapper.map(savedPokemon, PokemonDto.class);

        return savedPokemonDto;
    }

    @Override
    public PokemonDto getPokemon(Long id) {

        //get() method returns Pokemon entity object
        // local variable of type Pokemon class and call it pokemon
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found with ID: " + id));

        return modelMapper.map(pokemon, PokemonDto.class);
    }

    // related to getAllPokemons() method in PokemonService.java
    @Override
    public List<PokemonDto> getAllPokemons() {

        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream().map((pokemon) -> modelMapper.map(pokemon, PokemonDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, Long id) { // pokemonDto here contains all the updated info that is sent by the client

        // retrieve existing Pokemon record from the database
        // if a Pokemon with a given ID does not exist, throw the exception error message
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found with id: " + id));
        pokemon.setName(pokemonDto.getName());
        pokemon.setDescription(pokemonDto.getDescription());
        pokemon.setFinal_evolution(pokemon.isFinal_evolution());

        // save the pokemon object (in Pokemon pokemon = ...) into database
        // save() performs both INSERT and UPDATE
        // if the object entity contains primary key, this save() method perform UPDATE operation
        // if the object entity does not contain PK, save() performs INSERT operation
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        return modelMapper.map(updatedPokemon, PokemonDto.class);
    }

    @Override
    public void deletePokemon(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found with id: " + id));
        pokemonRepository.deleteById(id);
    }

    // the finalEvolutionPokemon() method is only for marking an existing Pokemon
    @Override
    public PokemonDto isFinalEvolution(Long id) {

        // retrieve existing pokemon by ID
        // if a pokemon with a given ID does not exist, throw an exception message
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found with id " + id));

        // update the pokemon object (line 110)
        pokemon.setFinal_evolution(Boolean.TRUE);

        // set the True value for this column
        // save pokemon object in a database table
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        // the finalEvolutionPokemon() method returns PokemonDto
        // so convert the updatedPokemon entity object into PokemonDto
        // pass updatedPokemon as a source and PokemonDto as a destination
        return modelMapper.map(updatedPokemon, PokemonDto.class);
    }

    @Override
    public PokemonDto isNotFinalEvolution(Long id) {

        // first, retrieve the existing Pokemon from the database table
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found with id " + id));

        // update the Pokemon's final evolution status
        pokemon.setFinal_evolution(Boolean.FALSE);

        // call pokemonRepository to save this updated info
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        // convert the updatedPokemon JPA entity into PokemonDto
        return modelMapper.map(updatedPokemon, PokemonDto.class);
    }
}
