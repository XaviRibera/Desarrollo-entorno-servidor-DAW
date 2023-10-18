package com.cipfpmislata.movies.persistence;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Director;

public interface DirectorRepository {
    List<Director> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Director findDirectorById(int id);
    void insert(Director director);
    int getTotalNumberOfRecords();
}
