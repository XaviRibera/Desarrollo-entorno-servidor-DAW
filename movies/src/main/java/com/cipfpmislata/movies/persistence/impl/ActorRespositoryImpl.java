package com.cipfpmislata.movies.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.db.exception.DBConnectionException;
import com.cipfpmislata.movies.domain.entity.Actor;
import com.cipfpmislata.movies.exception.SQLStatmentException;
import com.cipfpmislata.movies.persistence.ActorRepository;

@Repository
public class ActorRespositoryImpl implements ActorRepository {

    @Override
    public List<Actor> getAll(Integer page, Integer pageSize){
        String SQL = "SELECT * FROM actors";
        if(page != null){
            int limit = pageSize;
            int offset = (page-1) * limit;
            SQL += String.format(" LIMIT %d, %d", offset, limit);
        };

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
            return actors;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Actor> findActorById(int id){
        final String SQL = "SELECT * FROM actors WHERE id = ?";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection,SQL,List.of(id));
            if(resultSet.next()){
                return Optional.of(
                        new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        resultSet.getInt("deathYear")
                        )
                );
            }else {
                return Optional.empty();
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

    @Override
    public int getTotalNumberOfRecords(){
        final String SQL = "SELECT COUNT(*) FROM actors";
        try (Connection connection = DBUtil.open()){
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
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        params.add(actor.getId());
        Connection connection = DBUtil.open();
        DBUtil.update(connection, SQL, params);
    }

    @Override
    public void delete(int id) {
        final String SQL = "DELETE FROM actors WHERE id = ?";
        Connection connection = DBUtil.open();
        DBUtil.delete(connection, SQL, List.of(id));
    }
}
