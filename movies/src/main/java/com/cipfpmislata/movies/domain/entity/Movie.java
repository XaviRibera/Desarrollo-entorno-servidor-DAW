package com.cipfpmislata.movies.domain.entity;

import java.util.List;

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
public class Movie {
    private int id;
    private String title;
    private int year;
    private String image;
    private int runtime;
    private String description;
    private Director director;
    private List<Character> characters;

    public Movie(int id, String title, int year, String image, int runtime, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
        this.runtime = runtime;
        this.description = description;
    }

    public Movie(String title, int year, String image, int runtime, String description) {
        this.title = title;
        this.year = year;
        this.image = image;
        this.runtime = runtime;
        this.description = description;
    }
}
