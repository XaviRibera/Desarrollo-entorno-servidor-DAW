package com.cipfpmislata.movies.domain.persistance;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Director;

public interface DirectorRepository {
    List<Director> getAll(Integer page, Integer page_size);
    Optional<Director> findDirectorById(int id);
    int insert(Director director);
    int getTotalNumberOfRecords();
    public void update(Director director);
    public void delete(int id);
    public Optional<Director> findByMovieId(int movieId);
}
