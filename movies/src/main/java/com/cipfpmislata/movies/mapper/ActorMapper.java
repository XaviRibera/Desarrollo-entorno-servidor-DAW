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

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    
    ActorDetailWeb toActorDetailWeb(Optional<Actor> optional);
    ActorListWeb toActorListWeb(Actor actor);
}
