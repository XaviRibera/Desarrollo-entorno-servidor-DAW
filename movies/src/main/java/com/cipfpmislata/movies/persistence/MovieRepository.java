package com.cipfpmislata.movies.persistence;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Movie;

public interface MovieRepository {
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Movie findByMovieId(int id);
    int getTotalNumberOfRecords();
}
