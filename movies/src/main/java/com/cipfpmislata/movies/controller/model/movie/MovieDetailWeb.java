package com.cipfpmislata.movies.controller.model.movie;

import java.util.List;

import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
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
public class MovieDetailWeb {
 
    private int id;
    private String title;
    private int year;
    private String image;
    private int runtime;
    private DirectorListWeb directorListWeb;
    private List<CharacterListWeb> charactersListWeb;
}