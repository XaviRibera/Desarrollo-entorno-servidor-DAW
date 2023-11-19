package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.actor.ActorListWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterCreateWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterDetailWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterUpdateWeb;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.persistence.model.ActorEntity;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    
    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "movieId", expression = "java(resultSet.getInt(\"movie_id\"))")
    @Mapping(target = "characterName", expression = "java(resultSet.getString(\"characters\"))")
    @Mapping(target = "actorEntity", ignore = true)
    CharacterEntity toCharacterEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "actor", expression = "java(mapActorEntityToActor(characterEntity.getActorEntity()))")
    Character toCharacter(CharacterEntity characterEntity);

    @Mapping(target = "actorListWeb", expression = "java(mapActorToActorListWeb(character.getActor()))")
    CharacterDetailWeb toCharacterDetailWeb(Character character);


    @Named("actorEntityToActor")
    default Actor mapActorEntityToActor(ActorEntity actorEntity){
        return ActorMapper.mapper.toActor(actorEntity);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "actor", expression = "java(mapActorListWebToActor(characterCreateWeb.getActorListWeb()))")
    Character toCharacter(CharacterCreateWeb characterCreateWeb);

    @Named("actorListWebToActor")
    default Actor mapActorListWebToActor(ActorListWeb actorListWeb){
        return ActorMapper.mapper.toActor(actorListWeb);
    }

    @Mapping(target = "actorListWeb", expression = "java(mapActorToActorListWeb(character.getActor()))")
    CharacterListWeb toCharacterListWeb(Character character);

    @Named("actorToActorListWeb")
    default ActorListWeb mapActorToActorListWeb(Actor actor){
        return ActorMapper.mapper.toActorListWeb(actor);
    }

    @Mapping(target = "actorEntity", expression = "java(mapActorToActorEntity(character.getActor()))")
    CharacterEntity toCharacterEntity(Character character);

    @Named("actorToActorEntity")
    default ActorEntity mapActorToActorEntity(Actor actor){
        return ActorMapper.mapper.toActorEntity(actor);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "actor", expression = "java(mapActorListWebToActor(characterUpdateWeb.getActorListWeb()))")
    Character toCharacter(CharacterUpdateWeb characterUpdateWeb);

    List<Character> toCharacters(List<CharacterCreateWeb> charactersCreateWeb);
}
