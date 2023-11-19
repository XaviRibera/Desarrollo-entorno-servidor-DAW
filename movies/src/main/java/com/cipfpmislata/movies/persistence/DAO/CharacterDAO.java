package com.cipfpmislata.movies.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.mapper.CharacterMapper;
import com.cipfpmislata.movies.persistence.model.ActorEntity;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;

@Component
public class CharacterDAO {
    
    public List<CharacterEntity> findByMovieId(Connection connection, int movieId) {
        List<CharacterEntity> characterEntities = new ArrayList<>();
        final String SQL = """
                SELECT am.* FROM actors_movies am Where am.movie_id = ?
            """;
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            if(!resultSet.next()) {
                return null;
            }
            do {
                characterEntities.add(CharacterMapper.mapper.toCharacterEntity(resultSet));
            } while (resultSet.next());
            return characterEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void insert(Connection connection, CharacterEntity characterEntity){
        final String SQL = "INSERT INTO actors_movies (movie_id,actor_id,characters) VALUES ( ?, ?, ? )";
        List<Object> params = new ArrayList<>();
        params.add(characterEntity.getMovieId());
        ActorEntity actorEntity = characterEntity.getActorEntity();
        params.add(actorEntity.getId());
        params.add(characterEntity.getCharacterName());
        DBUtil.insert(connection, SQL, params);
    }

    public void delete(Connection connection, int movieId){
        final String SQL = "DELETE FROM actors_movies WHERE movie_id = ?";
        DBUtil.delete(connection, SQL, List.of(movieId));
    }
}
