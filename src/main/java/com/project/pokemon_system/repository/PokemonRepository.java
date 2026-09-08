package com.project.pokemon_system.repository;

import com.project.pokemon_system.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;


/* when PokemonRepository extends JpaRepository, then the interface PokemonRepository will get CRUD methods
  to perform CRUD database operations for this Pokemon JPA entity <Pokemon, Long> */
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
