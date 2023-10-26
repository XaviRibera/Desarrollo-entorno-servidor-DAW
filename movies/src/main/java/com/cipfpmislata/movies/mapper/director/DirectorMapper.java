package com.cipfpmislata.movies.mapper.director;

import org.mapstruct.factory.Mappers;

import com.cipfpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.mapper.movie.MovieMapper;

public interface DirectorMapper {
    
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);

    DirectorDetailWeb toDirectorDetailWeb(Director director);
    DirectorListWeb toDirectorListWeb(Director director);
}
