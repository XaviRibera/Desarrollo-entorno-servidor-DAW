package com.cipfpmislata.movies.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Director {

    int id;
    String name;
    int birthYear;
    int deathYear;

    public Director(int id, String name, int birthYear, int deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Director(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
    
}
