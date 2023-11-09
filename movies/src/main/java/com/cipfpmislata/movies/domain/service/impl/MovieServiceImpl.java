package com.cipfpmislata.movies.domain.service.impl;

import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.domain.service.MovieService;
import com.cipfpmislata.movies.persistence.MovieRepository;

import java.util.List;
import java.util.Optional;

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
        return movieRepository.findByMovieId(id);
    }

    @Override
    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();
    }
}
