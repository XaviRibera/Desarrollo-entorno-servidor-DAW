package com.cipfpmislata.movies.domain.service;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Actor;

public interface ActorService {
    public List<Actor> getAll(Integer page, Integer page_size);
    public Optional<Actor> findByActorId(int id);
    int getTotalNumberOfRecords();
    public void insert(Actor actor);
    public void update(Actor actor);
    public void delete(int id);
}
