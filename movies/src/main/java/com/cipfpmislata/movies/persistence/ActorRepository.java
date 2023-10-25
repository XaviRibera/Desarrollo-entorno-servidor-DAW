package com.cipfpmislata.movies.persistence;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Actor;

public interface ActorRepository {
    public List<Actor> getAll();
    public Optional<Actor> findActorById(int id);
    public void insert(Actor actor);
    public int getTotalNumberOfRecords();
    public void update(Actor actor);
    public void delete(int id);
}
