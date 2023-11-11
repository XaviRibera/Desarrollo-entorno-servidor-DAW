package com.cipfpmislata.movies.controller.model.movie;

import java.util.List;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.entity.Director;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDetailWeb {
 
    private int id;
    private String title;
    private int year;
    private int runtime;
    private Director director;
    private List<Actor> actors;
}