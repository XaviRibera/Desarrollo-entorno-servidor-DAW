package com.cipfpmislata.movies.controller.model.actor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorCreateWeb {
 
    private String name;
    private int birthYear;
    private Integer deathYear;
}