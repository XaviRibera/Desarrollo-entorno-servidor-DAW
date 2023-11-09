package com.cipfpmislata.movies.domain.service;

import com.cipfpmislata.movies.domain.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll(Integer page, Integer page_size);
    Movie findByMovieId(int id);
    int getTotalNumberOfRecords();
}
