package com.cipfpmislata.movies.persistence.model;

import java.sql.Connection;

import com.cipfpmislata.movies.persistence.DAO.ActorDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEntity {
    
    private int id;
    private int movieId;
    private String characterName;
    private ActorEntity actorEntity;

    public CharacterEntity(int id, int movieId, String characterName){
        this.id = id;
        this.movieId = movieId;
        this.characterName = characterName;
    }

    public ActorEntity getActorEntity(Connection connection, ActorDAO actorDAO){
        if(this.actorEntity == null){
            this.actorEntity = actorDAO.findByCharacterId(connection, this.id).orElse(null);
        }
        return this.actorEntity;
    }
}
