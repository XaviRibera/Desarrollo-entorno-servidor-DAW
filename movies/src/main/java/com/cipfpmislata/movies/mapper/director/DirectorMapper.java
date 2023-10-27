package com.cipfpmislata.movies.mapper.director;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.domain.entity.Director;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorDetailWeb toDirectorDetailWeb(Optional<Director> director);
    DirectorListWeb toDirectorListWeb(Director director);
}
