package com.cipfpmislata.movies.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.mapper.ActorMapper;
import com.cipfpmislata.movies.persistence.model.ActorEntity;

@Component
public class ActorDAO {
    
    public List<ActorEntity> getAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM actors";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<ActorEntity> ActorEntities = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                ActorEntities.add(ActorMapper.mapper.toActorEntity(resultSet));
            }
            return ActorEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public int insert(Connection connection, ActorEntity actorEntity) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        int id = DBUtil.insert(connection, SQL, params);
        return id;
    }

    public Optional<ActorEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next()? ActorMapper.mapper.toActorEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void update(Connection connection, ActorEntity actorEntity) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        params.add(actorEntity.getId());
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }

    public void delete(Connection connection, int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

    public List<ActorEntity> findByMovieId(Connection connection, int movieId) {
        List<ActorEntity> actorEntities = new ArrayList<>();
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
                actorEntities.add(ActorMapper.mapper.toActorEntity(resultSet));
            } while (resultSet.next());
            return actorEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
