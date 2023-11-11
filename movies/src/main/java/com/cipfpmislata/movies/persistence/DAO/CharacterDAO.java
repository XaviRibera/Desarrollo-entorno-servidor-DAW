package com.cipfpmislata.movies.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.mapper.ActorMapper;
import com.cipfpmislata.movies.mapper.CharacterMapper;
import com.cipfpmislata.movies.persistence.model.ActorEntity;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;

@Component
public class CharacterDAO {
    
    public List<CharacterEntity> findByMovieId(Connection connection, int movieId) {
        /*
        List<CharacterEntity> characterEntities = new ArrayList<>();
        final String SQL = """
                SELECT a.* FROM actors a
                INNER JOIN actors_movies am ON am.actor_id = a.id
                INNER JOIN movies m ON m.id = am.movie_id AND m.id = ?
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
        */
        return null;
    }
}
