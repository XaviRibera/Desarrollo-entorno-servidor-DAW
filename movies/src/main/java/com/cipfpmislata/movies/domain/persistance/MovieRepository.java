package com.cipfpmislata.movies.domain.persistance;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Movie;

public interface MovieRepository {
    public List<Movie> getAll(Integer page, Integer page_size);
    Optional<Movie> findByMovieId(int id);
    int getTotalNumberOfRecords();
    int insert(Movie movie);
}
