package com.cipfpmislata.movies.controller.model.director;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorUpdateWeb {

    private String name;
    private int birthYear;
    private Integer deathYear;
}