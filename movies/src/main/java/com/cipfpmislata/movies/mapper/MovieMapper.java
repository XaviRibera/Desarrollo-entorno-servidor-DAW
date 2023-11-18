package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieListWeb;
import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;
import com.cipfpmislata.movies.persistence.model.DirectorEntity;
import com.cipfpmislata.movies.persistence.model.MovieEntity;

@Mapper(componentModel = "spring")
public interface MovieMapper {
 
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
 
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "image", expression = "java(resultSet.getString(\"image\"))")
    @Mapping(target = "runtime", expression = "java(resultSet.getInt(\"runtime\"))")
    @Mapping(target = "description", expression = "java(resultSet.getString(\"description\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    MovieEntity toMovieEntity(Movie movie);
    
    @Mapping(target = "id", expression = "java(movieEntity.getId())")
    @Mapping(target = "title", expression = "java(movieEntity.getTitle())")
    @Mapping(target = "year", expression = "java(movieEntity.getYear())")
    @Mapping(target = "image", expression = "java(movieEntity.getImage())")
    @Mapping(target = "description", expression = "java(movieEntity.getDescription())")
    @Mapping(target = "director", expression = "java(mapDirectorEntityToDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characters", expression = "java(mapCharactersEntitiesToCharacters(movieEntity.getCharactersEntities()))")
    Movie toMovie(MovieEntity movieEntity);

    @Mapping(target =  "title", expression = "java(movieCreateWeb.getTitle())")
    @Mapping(target = "year", expression = "java(movieCreateWeb.getYear())")
    @Mapping(target = "image", expression = "java(movieCreateWeb.getImage())")
    @Mapping(target = "runtime", expression = "java(movieCreateWeb.getRuntime())")
    @Mapping(target = "description", expression = "java(movieCreateWeb.getDescription())")
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Named("directorEntityToDirector")
    default Director mapDirectorEntityToDirector(DirectorEntity directorEntity){
        return DirectorMapper.mapper.toDirector(directorEntity);
    }

    @Named("charactersEntitiesToCharacters")
    default List<Character> mapCharactersEntitiesToCharacters(List<CharacterEntity> characterEntities){
        return characterEntities.stream()
                .map(CharacterMapper.mapper::toCharacter)
                .toList();
    }
    
    MovieListWeb toMovieListWeb(Movie movie);

    @Mapping(target = "id", expression = "java(movie.getId())")
    @Mapping(target = "title", expression = "java(movie.getTitle())")
    @Mapping(target = "year", expression = "java(movie.getYear())")
    @Mapping(target = "image", expression = "java(movie.getImage())")
    @Mapping(target = "runtime", expression = "java(movie.getRuntime())")
    @Mapping(target = "directorListWeb", expression = "java(mapDirectorToDirectorListWeb(movie.getDirector()))")
    @Mapping(target = "charactersListWeb", expression = "java(mapCharactersToCharactersListWeb(movie.getCharacters()))")
    MovieDetailWeb toMovieDetailWeb(Movie movie);

    @Named("directorToDirectorListWeb")
    default DirectorListWeb mapDirectorToDirectorListWeb(Director director){
        return DirectorMapper.mapper.toDirectorListWeb(director);
    }

    @Named("charactersToCharactersListWeb")
    default List<CharacterListWeb> mapCharactersToCharactersListWeb(List<Character> characters){
        return characters.stream()
                .map(CharacterMapper.mapper::toCharacterListWeb)
                .toList();
    }
}