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

    ActorEntity toActorEntity(Actor actor);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;

    Actor toActor(ActorEntity actorEntity);

    @Mapping(target = "birthYear", ignore = true)
    @Mapping(target = "deathYear", ignore = true)
    Actor toActor(ActorListWeb actorListWeb);
    
    ActorDetailWeb toActorDetailWeb(Actor actor);

    @Mapping(target = "id", expression ="java(actor.getId())")
    @Mapping(target = "name", expression = "java(actor.getName())")
    ActorListWeb toActorListWeb(Actor actor);

    @Mapping(target = "id", ignore = true)
    Actor toActor(ActorCreateWeb actorCreateWeb);
    Actor toActor(ActorUpdateWeb actorUpdateWeb);
}
