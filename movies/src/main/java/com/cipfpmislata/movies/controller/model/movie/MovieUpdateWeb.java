package com.cipfpmislata.movies.controller.model.movie;

import java.util.List;

import com.cipfpmislata.movies.controller.model.character.CharacterUpdateWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateWeb {
    
    private String title;
    private int year;
    private String image;
    private int runtime;
    private String description;
    private DirectorListWeb directorListWeb;
    private List<CharacterUpdateWeb> CharactersUpdateWeb;
}
