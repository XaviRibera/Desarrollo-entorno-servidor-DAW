package com.cipfpmislata.movies.controller.model.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCreateWeb {
    private String characterName;
    private int actorId;
}
