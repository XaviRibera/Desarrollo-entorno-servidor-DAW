package com.cipfpmislata.movies.controller.model.character;

import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCreateWeb {
    private int movieId;
    private String characterName;
    private ActorListWeb actorListWeb;
}
