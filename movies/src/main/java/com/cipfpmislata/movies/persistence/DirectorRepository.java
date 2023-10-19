package com.cipfpmislata.movies.persistence;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Director;

public interface DirectorRepository {
    List<Director> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Director findDirectorById(int id);
    int insert(Director director);
    int getTotalNumberOfRecords();
    public void update(Director director);
}
