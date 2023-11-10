package com.cipfpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.db.exception.DBConnectionException;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.persistance.DirectorRepository;
import com.cipfpmislata.movies.exception.SQLStatmentException;
import com.cipfpmislata.movies.mapper.DirectorMapper;
import com.cipfpmislata.movies.persistence.DAO.DirectorDAO;
import com.cipfpmislata.movies.persistence.model.DirectorEntity;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public List<Director> getAll(Integer page, Integer pageSize){
        try(Connection connection = DBUtil.open(true)) {
            List<DirectorEntity> directorEntities = directorDAO.getAll(connection, page, pageSize);
            List<Director> directors = directorEntities.stream()
                    .map(directorEntity -> DirectorMapper.mapper.toDirector(directorEntity))
                    .toList();
            return directors;
 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Director> findDirectorById(int id){
        try (Connection connection = DBUtil.open(true)){
            Optional<DirectorEntity> directorEntity = directorDAO.find(connection, id);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int insert(Director director) {
        try (Connection connection = DBUtil.open(true)){
            return directorDAO.insert(connection, DirectorMapper.mapper.toDirectorEntity(director));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords(){
        final String SQL = "SELECT COUNT(*) FROM directors";
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            resultSet.next();
            return (int) resultSet.getInt(1);
        }catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public void update(Director director) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.update(connection, DirectorMapper.mapper.toDirectorEntity(director));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            directorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Director> findByMovieId(int movieId) {
        try(Connection connection= DBUtil.open(true)) {
            Optional<DirectorEntity> directorEntity = directorDAO.findByMovieId(connection, movieId);
            if(directorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(DirectorMapper.mapper.toDirector(directorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

