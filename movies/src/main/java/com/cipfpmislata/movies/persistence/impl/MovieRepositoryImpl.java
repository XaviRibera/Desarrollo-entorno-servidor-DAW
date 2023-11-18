package com.cipfpmislata.movies.persistence.impl;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.domain.persistance.MovieRepository;
import com.cipfpmislata.movies.mapper.MovieMapper;
import com.cipfpmislata.movies.persistence.DAO.ActorDAO;
import com.cipfpmislata.movies.persistence.DAO.CharacterDAO;
import com.cipfpmislata.movies.persistence.DAO.DirectorDAO;
import com.cipfpmislata.movies.persistence.DAO.MovieDAO;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;
import com.cipfpmislata.movies.persistence.model.MovieEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    
    @Autowired
    MovieDAO movieDAO;

    @Autowired
    DirectorDAO directorDAO;

    @Autowired
    CharacterDAO characterDAO;

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.getAll(connection, page, pageSize);
            List<Movie> movies = movieEntities.stream()
                    .map(movieEntity -> MovieMapper.mapper.toMovie(movieEntity))
                    .toList();
            return movies;
 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> findByMovieId(int id){
        try (Connection connection = DBUtil.open(true)){
            Optional<MovieEntity> movieEntity = movieDAO.find(connection, id);
            if(movieEntity.isEmpty()) {
                return Optional.empty();
            }
            movieEntity.get().getDirectorEntity(connection, directorDAO);
            List<CharacterEntity> characters = movieEntity.get().getCharactersEntities(connection, characterDAO);
            for(CharacterEntity characterEntity : characters){
                characterEntity.getActorEntity(connection, actorDAO);
            }
            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)) {
            return movieDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Movie movie){
        try(Connection connection = DBUtil.open(true)){
            return movieDAO.insert(connection, MovieMapper.mapper.toMovieEntity(movie));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
