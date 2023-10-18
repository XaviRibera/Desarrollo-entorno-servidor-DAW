package com.cipfpmislata.movies.domain.service;

import java.util.List;

import com.cipfpmislata.movies.domain.entity.Actor;

public interface ActorService {
    public List<Actor> getAll();
    public Actor findByActorId(int id);
    public void insert(Actor actor);
}
