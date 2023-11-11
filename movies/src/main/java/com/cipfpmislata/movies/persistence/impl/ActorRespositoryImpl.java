package com.cipfpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.db.exception.DBConnectionException;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.persistance.ActorRepository;
import com.cipfpmislata.movies.exception.SQLStatmentException;
import com.cipfpmislata.movies.mapper.ActorMapper;
import com.cipfpmislata.movies.mapper.DirectorMapper;
import com.cipfpmislata.movies.persistence.DAO.ActorDAO;
import com.cipfpmislata.movies.persistence.model.ActorEntity;
import com.cipfpmislata.movies.persistence.model.DirectorEntity;

@Repository
public class ActorRespositoryImpl implements ActorRepository {

    @Autowired
    ActorDAO actorDAO;

    @Override
    public List<Actor> getAll(Integer page, Integer pageSize){
        try(Connection connection = DBUtil.open(true)) {
            List<ActorEntity> actorEntities = actorDAO.getAll(connection, page, pageSize);
            List<Actor> actors = actorEntities.stream()
                    .map(actorEntity -> ActorMapper.mapper.toActor(actorEntity))
                    .toList();
            return actors;
 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Actor> findActorById(int id){
        try (Connection connection = DBUtil.open(true)){
            Optional<ActorEntity> actorEntity = actorDAO.find(connection, id);
            if(actorEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(ActorMapper.mapper.toActor(actorEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int insert(Actor actor) {
        try (Connection connection = DBUtil.open(true)){
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords(){
        final String SQL = "SELECT COUNT(*) FROM actors";
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
    public void update(Actor actor) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection= DBUtil.open(true)) {
            actorDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
