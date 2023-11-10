package com.cipfpmislata.movies.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.persistence.model.DirectorEntity;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorEntity toDirectorEntity(Director director);
     
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java(resultSet.getInt(\"deathYear\"))")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;
     
    Director toDirector(DirectorEntity directorEntity);

    DirectorDetailWeb toDirectorDetailWeb(Director director);
    DirectorListWeb toDirectorListWeb(Director director);
    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
}
