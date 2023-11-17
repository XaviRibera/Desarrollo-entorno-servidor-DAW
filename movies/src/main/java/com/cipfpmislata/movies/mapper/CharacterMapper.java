package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.persistence.model.ActorEntity;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    
    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    CharacterEntity toCharacterEntity(Character character);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "movieId", expression = "java(resultSet.getInt(\"movie_id\"))")
    @Mapping(target = "characterName", expression = "java(resultSet.getString(\"characters\"))")
    CharacterEntity toCharacterEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "id", expression = "java(characterEntity.getId())")
    @Mapping(target = "movieId", expression = "java(characterEntity.getMovieId())")
    @Mapping(target = "actor", expression = "java(mapActorEntityToActor(characterEntity.getActorEntity()))")
    @Mapping(target = "characterName", expression = "java(characterEntity.getCharacterName())")
    Character toCharacter(CharacterEntity characterEntity);


    @Named("actorEntityToActor")
    default Actor mapActorEntityToActor(ActorEntity actorEntity){
        return ActorMapper.mapper.toActor(actorEntity);
    }

    @Mapping(target = "characterName", expression = "java(character.getCharacterName())")
    @Mapping(target = "actorListWeb", expression = "java(mapActorToActorListWeb(character.getActor()))")
    CharacterListWeb toCharacterListWeb(Character character);

    @Named("actorToActorListWeb")
    default ActorListWeb mapActorToActorListWeb(Actor actor){
        return ActorMapper.mapper.toActorListWeb(actor);
    }
}
