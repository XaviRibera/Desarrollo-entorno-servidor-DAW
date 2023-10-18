package com.cipfpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.db.exception.DBConnectionException;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.exception.SQLStatmentException;
import com.cipfpmislata.movies.persistence.ActorRepository;

@Repository
public class ActorRespositoryImpl implements ActorRepository {

    @Override
    public List<Actor> getAll(){
        final String SQL = "SELECT * FROM actors";
        List<Actor> actors = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                actors.add(
                        new Actor(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                resultSet.getInt("deathYear")
                        )
                );
            }
            DBUtil.close(connection);
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Actor findActorById(int id){
        final String SQL = "SELECT * FROM actors WHERE id = ?";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection,SQL,List.of(id));
            if(resultSet.next()){
                return new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                );
            }else{
                return null;
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void insert(Actor actor) {
        final String SQL = "INSERT INTO actors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        try (Connection connection = DBUtil.open()){
            DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e) {
            throw new SQLStatmentException("SQL: " + SQL);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
