package com.cipfpmislata.movies.persistence.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.mapper.MovieMapper;
import com.cipfpmislata.movies.persistence.model.MovieEntity;

@Component
public class MovieDAO {
 
    public List<MovieEntity> getAll(Connection connection, Integer page, Integer pageSize) {
        List<Object> params = null;
        String sql = "SELECT * FROM movies";
        if(page != null) {
            int offset = (page - 1) * pageSize;
            sql += " LIMIT ?, ?";
            params = List.of(offset, pageSize);
        }
        List<MovieEntity> movieEntities = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
 
    public Optional<MovieEntity> find(Connection connection, int id) {
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next()? MovieMapper.mapper.toMovieEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Optional<MovieEntity> findByName(Connection connection, String name) {
        final String SQL = "SELECT * FROM movies WHERE title = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(name));
            return Optional.ofNullable(resultSet.next()? MovieMapper.mapper.toMovieEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
 
    public int getTotalNumberOfRecords(Connection connection) {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("SQL: " + SQL);
        }
    }

    public int insert(Connection connection, MovieEntity movieEntity){
        final String SQL = "INSERT INTO movies (title,year,image,runtime,description) VALUES ( ?, ?, ?, ?, ? )";
        List<Object> params = new ArrayList<>();
        params.add(movieEntity.getTitle());
        params.add(movieEntity.getYear());
        params.add(movieEntity.getImage());
        params.add(movieEntity.getRuntime());
        params.add(movieEntity.getDescription());
        int id = DBUtil.insert(connection, SQL, params);
        return id;
    }

    public void delete(Connection connection, int movieId){
        final String SQL = "DELETE FROM movies WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(movieId));
    }

    public void update(Connection connection, MovieEntity movieEntity){
        final String SQL = "UPDATE movies SET title = ?, year = ?, image = ?, runtime = ?, description = ?, director_id = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(movieEntity.getTitle());
        params.add(movieEntity.getYear());
        params.add(movieEntity.getImage());
        params.add(movieEntity.getRuntime());
        params.add(movieEntity.getDescription());
        params.add(movieEntity.getDirectorEntity().getId());
        params.add(movieEntity.getId());
        DBUtil.update(connection, SQL, params);
    }
}