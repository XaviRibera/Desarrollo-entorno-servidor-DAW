package com.cipfpmislata.movies.mapper.actor;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;
import com.cipfpmislata.movies.domain.entity.Actor;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    
    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    ActorDetailWeb toActorDetailWeb(Optional<Actor> actor);
    ActorListWeb toActorListWeb(Actor actor);
}
