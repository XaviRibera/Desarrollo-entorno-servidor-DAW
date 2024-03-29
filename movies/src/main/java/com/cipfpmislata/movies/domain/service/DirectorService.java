package com.cipfpmislata.movies.domain.service;

import java.util.List;

import com.cipfpmislata.movies.domain.entity.Director;

public interface DirectorService {
    List<Director> getAll(Integer page, Integer page_size);
    Director findByDirectorId(int id);
    int insert(Director director);
    int getTotalNumberOfRecords();
    void update(Director director);
    void delete(int id);
}
