package com.cipfpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.persistence.DAO.CharacterDAO;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;
import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.domain.persistance.CharacterRespository;
import com.cipfpmislata.movies.mapper.CharacterMapper;

@Repository
public class CharacterRepositoryImpl implements CharacterRespository{
    
    @Autowired
    CharacterDAO characterDAO;

    public List<Character> findByMovieId(int movieId){
        try(Connection connection = DBUtil.open(true)){
            List<CharacterEntity> characterEntities = characterDAO.findByMovieId(connection, movieId);
            List<Character> characters = characterEntities.stream()
                .map(characterEntity -> CharacterMapper.mapper.toCharacter(characterEntity))
                .toList();
            return characters;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } 
}
