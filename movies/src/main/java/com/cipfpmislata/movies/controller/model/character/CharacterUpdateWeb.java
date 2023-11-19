package com.cipfpmislata.movies.controller.model.character;

import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;

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
public class CharacterUpdateWeb {
    private int movieId;
    private ActorListWeb actorListWeb;
    private String characterName;
}
