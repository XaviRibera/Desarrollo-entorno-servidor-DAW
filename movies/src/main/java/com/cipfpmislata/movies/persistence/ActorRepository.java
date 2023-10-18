package com.cipfpmislata.movies.persistence;

import java.util.List;

import com.cipfpmislata.movies.domain.entity.Actor;

public interface ActorRepository {
    public List<Actor> getAll();
    public Actor findActorById(int id);
    public void insert(Actor actor);
}
