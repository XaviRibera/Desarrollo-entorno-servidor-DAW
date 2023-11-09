package com.cipfpmislata.movies.persistence;

import java.util.List;

import com.cipfpmislata.movies.domain.entity.Movie;

public interface MovieRepository {
    public List<Movie> getAll(Integer page, Integer page_size);
    Movie findByMovieId(int id);
    int getTotalNumberOfRecords();
}
