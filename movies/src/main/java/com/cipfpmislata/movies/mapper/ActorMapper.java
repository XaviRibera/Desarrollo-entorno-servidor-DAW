package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.actor.ActorCreateWeb;
import com.cipfpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;
import com.cipfpmislata.movies.controller.model.actor.ActorUpdateWeb;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.persistence.model.ActorEntity;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    
    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    ActorEntity toDirectorEntity(Actor actor);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;

    Actor toActor(ActorEntity actorEntity);
    
    ActorDetailWeb toActorDetailWeb(Actor actor);
    ActorListWeb toActorListWeb(Actor actor);
    Actor toActor(ActorCreateWeb actorCreateWeb);
    Actor toActor(ActorUpdateWeb actorUpdateWeb);
}
