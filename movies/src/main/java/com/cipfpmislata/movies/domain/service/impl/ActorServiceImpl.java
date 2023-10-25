package com.cipfpmislata.movies.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.service.ActorService;
import com.cipfpmislata.movies.exception.ResourceNotFoundException;
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
    public Optional<Actor> findByActorId(int id){
        return actorRepository.findActorById(id);
    }

    @Override
    public void insert(Actor actor){
        actorRepository.insert(actor);
    }

    @Override
    public void update(Actor actor){
        actorRepository.findActorById(actor.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + actor.getId()));
        actorRepository.update(actor);
    }

    @Override
    public void delete(int id) {
        actorRepository.findActorById(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        actorRepository.delete(id);
    }
}
