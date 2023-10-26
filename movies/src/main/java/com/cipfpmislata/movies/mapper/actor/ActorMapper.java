package com.cipfpmislata.movies.mapper.actor;

import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.mapper.movie.MovieMapper;

public interface ActorMapper {
    
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    ActorDetailWeb toActorListWeb(Actor actor);
}
