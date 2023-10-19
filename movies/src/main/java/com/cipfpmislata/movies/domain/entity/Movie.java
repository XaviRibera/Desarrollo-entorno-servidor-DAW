package com.cipfpmislata.movies.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private int id;
    private String title;
    private int year;
    private String image;
    private int runtime;
    private String description;
    private int director_id;

    public Movie(int id, String title, int year, String image, int runtime, String description, int director_id) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
        this.runtime = runtime;
        this.description = description;
        this.director_id = director_id;
    }
    
}
