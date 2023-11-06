package com.cipfpmislata.movies.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.cipfpmislata.movies.domain.entity.Director;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    
    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorDetailWeb toDirectorDetailWeb(Optional<Director>irectorLi);
    DirectorListWeb toDirectorListWeb(Director director);
    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
}
