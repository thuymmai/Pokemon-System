package com.project.pokemon_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor //to create no-argument constructor for this class
@AllArgsConstructor //to create an all-argument constructor
@Entity //specify that this class is an entity
@Table(name = "pokemon-general") //specify the table details
public class Pokemon {

    //define instance variables

    @Id // indicate "id" is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increments PK
    private Long id;

    @Column(nullable = false) //create a column in the database, make sure it is not null
    private String name;

    @Column(nullable = false)
    private String description;

    private boolean final_evolution;
}
