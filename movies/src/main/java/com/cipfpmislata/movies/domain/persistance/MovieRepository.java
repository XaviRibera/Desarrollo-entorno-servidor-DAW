package com.cipfpmislata.movies.domain.persistance;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.domain.entity.Movie;

public interface MovieRepository {
    Object findByMovieName = null;
    public List<Movie> getAll(Integer page, Integer page_size);
    Optional<Movie> findByMovieId(int id);
    Optional<Movie> findByMovieName(String name);
    List<Character> getCharacterByMovieId(int id);
    Optional<Character> findByCharacterId(int id);
    int getTotalNumberOfRecords();
    int insertMovie(Movie movie);
    void deleteMovie(int movieId);
    void deleteCharacterByMovieId(int movieId);
    void deleteCharacterById(int characterId);
    void updateMovie(Movie movie);
    void insertCharacter(List<com.cipfpmislata.movies.domain.entity.Character> characters);
}
