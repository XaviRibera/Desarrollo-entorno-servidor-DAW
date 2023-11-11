package com.cipfpmislata.movies.controller.model.character;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDetailWeb {
    private int id;
    private int movieId;
    private Actor actor;
    private String characterName;
}
