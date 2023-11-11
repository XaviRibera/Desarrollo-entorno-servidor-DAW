package com.cipfpmislata.movies.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    private int id;
    private int movieId;
    private Actor actor;
    private String characterName;

    public Character(int id, int movieId, String characterName){
        this.id = id;
        this.movieId = movieId;
        this.characterName = characterName;
    }
}
