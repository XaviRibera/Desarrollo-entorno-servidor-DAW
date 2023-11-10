package com.cipfpmislata.movies.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.persistance.ActorRepository;
import com.cipfpmislata.movies.domain.service.ActorService;
import com.cipfpmislata.movies.exception.ResourceNotFoundException;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Actor> getAll(Integer page, Integer pageSize){
        return actorRepository.getAll(page,pageSize);
    }

    @Override
    public Actor findByActorId(int id){
        return actorRepository.findActorById(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
    }

    @Override
    public void insert(Actor actor){
        actorRepository.insert(actor);
    }

    @Override
    public int getTotalNumberOfRecords(){
        return actorRepository.getTotalNumberOfRecords();
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
