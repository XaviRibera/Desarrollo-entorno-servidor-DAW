package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;
import com.cipfpmislata.movies.domain.entity.Actor;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    
    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);
    
    ActorDetailWeb toActorDetailWeb(Actor actor);
    ActorListWeb toActorListWeb(Actor actor);
}
