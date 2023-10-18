package com.cipfpmislata.movies.domain.service;

import com.cipfpmislata.movies.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Movie findByMovieId(int id);
    int getTotalNumberOfRecords();
}
