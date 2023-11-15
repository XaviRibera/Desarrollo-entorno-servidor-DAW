package com.cipfpmislata.movies.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity {
    
    private int id;
    private int movieId;
    private ActorEntity actorEntity;
    private String characterName;
}
