package com.cipfpmislata.movies.persistence.impl;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.domain.persistance.MovieRepository;
import com.cipfpmislata.movies.mapper.CharacterMapper;
import com.cipfpmislata.movies.mapper.MovieMapper;
import com.cipfpmislata.movies.persistence.DAO.ActorDAO;
import com.cipfpmislata.movies.persistence.DAO.CharacterDAO;
import com.cipfpmislata.movies.persistence.DAO.DirectorDAO;
import com.cipfpmislata.movies.persistence.DAO.MovieDAO;
import com.cipfpmislata.movies.persistence.model.CharacterEntity;
import com.cipfpmislata.movies.persistence.model.MovieEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
            for(MovieEntity movieEntity : movieEntities){
                movieEntity.getDirectorEntity(connection, directorDAO);
                List<CharacterEntity> charactersEntities = movieEntity.getCharactersEntities(connection, characterDAO);
                for(CharacterEntity characterEntity : charactersEntities){
                    characterEntity.getActorEntity(connection, actorDAO);
                }
            }
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
            List<CharacterEntity> charactersEntities = movieEntity.get().getCharactersEntities(connection, characterDAO);
            for(CharacterEntity characterEntity : charactersEntities){
                characterEntity.getActorEntity(connection, actorDAO);
            }
            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> findByMovieName(String name){
        try(Connection connection = DBUtil.open(true)){
            Optional<MovieEntity> movieEntity = movieDAO.findByName(connection, name);
            if(movieEntity.isEmpty()){
                return Optional.empty();
            }
            movieEntity.get().getDirectorEntity(connection, directorDAO);
            List<CharacterEntity> charactersEntities = movieEntity.get().getCharactersEntities(connection, characterDAO);
            for(CharacterEntity characterEntity : charactersEntities){
                characterEntity.getActorEntity(connection, actorDAO);
            }
            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Character> getCharacterByMovieId(int id){
        try(Connection connection = DBUtil.open(true)){
            List<CharacterEntity> characterEntities = characterDAO.findByMovieId(connection, id);
            List<Character> characters = new ArrayList<>();
            for(CharacterEntity characterEntity : characterEntities){
                characterEntity.getActorEntity(connection, actorDAO);
                characters.add(CharacterMapper.mapper.toCharacter(characterEntity));
            }
            return characters;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Character> findByCharacterId(int id){
        try(Connection connection = DBUtil.open(true)){
            Optional<CharacterEntity> characterEntity = characterDAO.findByCharacterId(connection, id);
            if(characterEntity.isEmpty()){
                return Optional.empty();
            }
            characterEntity.get().getActorEntity(connection, actorDAO);
            return Optional.of(CharacterMapper.mapper.toCharacter(characterEntity.get()));
        }catch (SQLException e) {
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
    public int insertMovie(Movie movie){
        try(Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int id = movieDAO.insert(connection, movieEntity);
            List<CharacterEntity> charactersEntities = movieEntity.getCharactersEntities();
            if(charactersEntities != null){
                for(CharacterEntity characterEntity : charactersEntities){
                    characterEntity.setMovieId(id);
                    characterDAO.insert(connection, characterEntity);
                }
            }
            connection.commit();
            return id;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMovie(int movieId){
        try(Connection connection = DBUtil.open(false)){
            characterDAO.deleteByMovieId(connection, movieId);
            movieDAO.delete(connection, movieId);
            connection.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacterByMovieId(int movieId){
        try(Connection connection = DBUtil.open(true)){
            characterDAO.deleteByMovieId(connection, movieId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacterById(int characterId){
        try(Connection connection = DBUtil.open(true)){
            characterDAO.deleteByCharacterId(connection, characterId);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMovie(Movie movie){
        try(Connection connection = DBUtil.open(false)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            movieDAO.update(connection, movieEntity);
            int movieId = movieEntity.getId();
            characterDAO.deleteByMovieId(connection, movieId);
            for(CharacterEntity characterEntity : movieEntity.getCharactersEntities()){
                characterEntity.setMovieId(movieId);
                characterDAO.insert(connection, characterEntity);
            }
            connection.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertCharacter(List<Character> characters) {
        try(Connection connection = DBUtil.open(false)){
            for(Character character : characters){
                characterDAO.insert(connection, CharacterMapper.mapper.toCharacterEntity(character));
            }
            connection.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        
    }
}
