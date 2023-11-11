package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.character.CharacterDetailWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;

@Mapper(componentModel = "spring")
public class CharacterMapper {
    
    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    CharacterEntity toCharacterEntity(Character character);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "movieId", expression = "java(resultSet.getInt(\"movie_id\"))")
    @Mapping(target = "actorId", expression = "java(resultSet.getInt(\"actor_id\"))")
    @Mapping(target = "characterName", expression = "java(resultSet.getString(\"characters\"))")
    CharacterEntity toCharacterEntity(ResultSet resultSet) throws SQLException;

    Character toCharacter(CharacterEntity characterEntity);

    CharacterDetailWeb toCharacterDetailWeb(Character character);
    CharacterListWeb toCharacterListWeb(Character character);
}
