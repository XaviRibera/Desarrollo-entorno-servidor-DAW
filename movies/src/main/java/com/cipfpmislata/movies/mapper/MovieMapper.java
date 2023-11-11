package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieListWeb;
import com.cipfpmislata.movies.domain.entity.Movie;
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
    
    Movie toMovie(MovieEntity movieEntity);
    Movie toMovie(MovieCreateWeb movieCreateWeb);
    
    MovieListWeb toMovieListWeb(Movie movie);
    MovieDetailWeb toMovieDetailWeb(Movie movie);
}