package com.cipfpmislata.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.service.ActorService;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("")
    public List<Actor> getAll(){
        try {
            System.out.println(actorService.getAll());
            return actorService.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Actor find(@PathVariable("id") int id) {
        try {
            System.out.println(actorService.findByActorId(id));
            return actorService.findByActorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /*
    @GetMapping("/insert")
    public void insert(){
        try {
            Actor actor = null;
            actorService.insert(actor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    */
}
