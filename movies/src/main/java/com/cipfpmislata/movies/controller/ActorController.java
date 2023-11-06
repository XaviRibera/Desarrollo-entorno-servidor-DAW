package com.cipfpmislata.movies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.service.ActorService;
import com.cipfpmislata.movies.http_response.Response;
import com.cipfpmislata.movies.mapper.ActorMapper;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Value("${LIMIT}") 
    private int LIMIT;

    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize){
        int limit = (pageSize.isPresent())? pageSize.get(): LIMIT;
        int totalRecords = actorService.getTotalNumberOfRecords();

        List<Actor> actors = actorService.getAll();
        List<ActorListWeb> actorWeb = actors.stream()
                    .map(actor -> ActorMapper.mapper.toActorListWeb(actor))
                    .toList();

        return new Response(actorWeb, totalRecords, page, limit);
    }

    @GetMapping("/{id}")
    public Optional<Actor> find(@PathVariable("id") int id) {
        try {
            System.out.println(actorService.findByActorId(id));
            return actorService.findByActorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        actor.setId(id);
        actorService.update(actor);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        actorService.delete(id);
    }
}
