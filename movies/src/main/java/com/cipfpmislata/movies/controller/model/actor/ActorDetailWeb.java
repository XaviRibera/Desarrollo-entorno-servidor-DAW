package com.cipfpmislata.movies.controller.model.actor;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorDetailWeb {
 
    private int id;
    private String name;
    private int birthYear;
    private int deathYear;
}