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
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.exception.SQLStatmentException;
import com.cipfpmislata.movies.persistence.DirectorRepository;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public List<Director> getAll(Optional<Integer> page, Optional<Integer> page_size){
        String SQL = "SELECT * FROM directors";
        if(page.isPresent()){
            int limit = page_size.get();
            int offset = (page.get()-1) * limit;
            SQL += String.format(" LIMIT %d, %d", offset, limit);
        }
        List<Director> directors = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                directors.add(
                        new Director(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                resultSet.getInt("deathYear")
                        )
                );
            }
            return directors;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Director findDirectorById(int id){
        final String SQL = "SELECT * FROM directors WHERE id = ?";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection,SQL,List.of(id));
            if(resultSet.next()){
                return new Director(
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
    public void insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
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
        final String SQL = "SELECT COUNT(*) FROM directors";
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
}

