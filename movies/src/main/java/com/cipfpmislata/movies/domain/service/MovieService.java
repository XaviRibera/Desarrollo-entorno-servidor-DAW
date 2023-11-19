package com.cipfpmislata.movies.domain.service;

import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.domain.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll(Integer page, Integer page_size);
    Movie findByMovieId(int id);
    List<Character> getCharacterByMovieId(int id);
    Character findByCharacterId(int id);
    int getTotalNumberOfRecords();
    int insertMovie(Movie movie);
    void deleteMovie(int movieId);
    void deleteCharacterByMovieId(int movieId);
    void deleteCharacterById(int characterId);
    void updateMovie(Movie movie);
    void insertCharacter(List<Character> characters);
}
