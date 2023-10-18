package com.cipfpmislata.movies.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.service.ActorService;
import com.cipfpmislata.movies.persistence.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Actor> getAll(){
        return actorRepository.getAll();
    }

    @Override
    public Actor findByActorId(int id){
        return actorRepository.findActorById(id);
    }

    @Override
    public void insert(Actor actor){
        actorRepository.insert(actor);
    }
}
