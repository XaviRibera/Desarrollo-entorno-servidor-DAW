package com.cipfpmislata.movies.domain.persistance;

import java.util.List;
import java.util.Optional;

import com.cipfpmislata.movies.domain.entity.Actor;

public interface ActorRepository {
    public List<Actor> getAll(Integer page, Integer pageSize);
    public Optional<Actor> findActorById(int id);
    public int insert(Actor actor);
    public int getTotalNumberOfRecords();
    public void update(Actor actor);
    public void delete(int id);
}
