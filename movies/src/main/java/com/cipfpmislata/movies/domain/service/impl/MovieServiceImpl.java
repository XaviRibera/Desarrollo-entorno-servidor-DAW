package com.cipfpmislata.movies.domain.service.impl;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.domain.persistance.ActorRepository;
import com.cipfpmislata.movies.domain.persistance.CharacterRespository;
import com.cipfpmislata.movies.domain.persistance.DirectorRepository;
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

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private CharacterRespository characterRespository;

    @Override
    public List<Movie> getAll(Integer page, Integer page_size) {
        return movieRepository.getAll(page, page_size);
    }

    
    @Override
    public Movie findByMovieId(int id){
        /*
        Movie movie = movieRepository.findByMovieId(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));

        Director director = directorRepository.findByMovieId(id).orElse(null);
        movie.setDirector(director);

        List<Character> characters = characterRepository.findByMovieId(id);
        movie.setCharacters(characters);
        
        return movie;
        */
        return null;
    }
    @Override
    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();
    }
}
