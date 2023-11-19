package com.cipfpmislata.movies.domain.service.impl;

import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.domain.persistance.MovieRepository;
import com.cipfpmislata.movies.domain.service.MovieService;
import com.cipfpmislata.movies.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll(Integer page, Integer page_size) {
        return movieRepository.getAll(page, page_size);
    }

    
    @Override
    public Movie findByMovieId(int id){
        Movie movie = movieRepository.findByMovieId(id).orElseThrow(() -> new ResourceNotFoundException("Pelicula no encontrado con id: " + id));
        return movie;
    }

    @Override
    public List<Character> getCharacterByMovieId(int id){
         return movieRepository.getCharacterByMovieId(id);
    }

    @Override
    public Character findByCharacterId(int id){
        return movieRepository.findByCharacterId(id).orElseThrow(() -> new ResourceNotFoundException("Personaje no encontrado con id: " + id));
    }

    @Override
    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int insertMovie(Movie movie){
        return movieRepository.insertMovie(movie);
    }

    @Override
    public void deleteMovie(int movieId){
        movieRepository.deleteMovie(movieId);
    }

    @Override
    public void deleteCharacterByMovieId(int movieId){
        movieRepository.deleteCharacterByMovieId(movieId);
    }

    @Override
    public void deleteCharacterById(int characterId){
        movieRepository.deleteCharacterById(characterId);
    }

    @Override
    public void updateMovie(Movie movie){
        movieRepository.updateMovie(movie);
    }


    @Override
    public void insertCharacter(List<Character> characters) {
        movieRepository.insertCharacter(characters);
    }

    
}
