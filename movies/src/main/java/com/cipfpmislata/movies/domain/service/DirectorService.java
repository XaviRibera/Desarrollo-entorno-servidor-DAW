package com.cipfpmislata.movies.domain.service;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Director;

public interface DirectorService {
    List<Director> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Optional<Director> findByDirectorId(int id);
    int insert(Director director);
    int getTotalNumberOfRecords();
    public void update(Director director);
    public void delete(int id);
}
