package com.cipfpmislata.movies.persistence.model;

import java.sql.Connection;
import java.util.List;

import com.cipfpmislata.movies.persistence.DAO.CharacterDAO;
import com.cipfpmislata.movies.persistence.DAO.DirectorDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
 
    private int id;
    private String title;
    private int year;
    private String image;
    private int runtime;
    private String description;
    private DirectorEntity directorEntity;
    private List<CharacterEntity> charactersEntities;

    public DirectorEntity getDirectorEntity(Connection connection, DirectorDAO directorDAO){
        if(this.directorEntity == null){
            this.directorEntity = directorDAO.findByMovieId(connection, this.id).orElse(null);
        }
        return this.directorEntity;
    }

    public List<CharacterEntity> getCharactersEntities(Connection connection, CharacterDAO characterDAO){
        if(this.charactersEntities == null){
            this.charactersEntities = characterDAO.findByMovieId(connection, this.id);
        }
        return this.charactersEntities;
    }
}