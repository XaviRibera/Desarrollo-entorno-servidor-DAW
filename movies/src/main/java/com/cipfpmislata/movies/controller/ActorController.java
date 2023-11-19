package com.cipfpmislata.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cipfpmislata.movies.controller.model.actor.ActorCreateWeb;
import com.cipfpmislata.movies.controller.model.actor.ActorDetailWeb;
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
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize){
        pageSize = (pageSize != null)? pageSize: LIMIT;
        int totalRecords = actorService.getTotalNumberOfRecords();

        List<Actor> actors = (page != null) ? actorService.getAll(page, pageSize) : actorService.getAll(null, null);
        List<ActorListWeb> actorsWeb = actors.stream()
                    .map(actor -> ActorMapper.mapper.toActorListWeb(actor))
                    .toList();

        Response response = Response.builder()
            .data(actorsWeb)
            .totalRecords(totalRecords)
            .build();
        return response;
    }

    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        ActorDetailWeb actorDetailWeb = ActorMapper.mapper.toActorDetailWeb(actorService.findByActorId(id));
        return Response.builder().data(actorDetailWeb).build();
    }

    @GetMapping("/insert")
    public Response insert(@RequestBody ActorCreateWeb actorCreateWeb){
        int id = actorService.insert(ActorMapper.mapper.toActor(actorCreateWeb));
        ActorDetailWeb actorDetailWeb = new ActorDetailWeb(
            id,
            actorCreateWeb.getName(),
            actorCreateWeb.getBirthYear(),
            actorCreateWeb.getDeathYear()
    );
    
        return Response.builder().data(actorDetailWeb).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Actor actor) {
        actor.setId(id);
        actorService.update(actor);
    }
    
    //----------El Delete de Actor no se podria sin hacer validadiones----------\\
    /*
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        actorService.delete(id);
    }
    */
}
