package com.cipfpmislata.movies.controller.model.director;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorDetailWeb {
 
    public DirectorDetailWeb(int id2, String name2, int birthYear2, Integer deathYear2) {
    }
    private int id;
    private String name;
    private int birthYear;
    private int deathYear;
 
}