package com.cipfpmislata.movies.persistence.impl;

import com.cipfpmislata.movies.db.DBUtil;
import com.cipfpmislata.movies.db.exception.DBConnectionException;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.exception.SQLStatmentException;
import com.cipfpmislata.movies.persistence.MovieRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    

    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size) {
        String sql = "SELECT * FROM movies";
        if(page.isPresent()) {
            int limit = page_size.get();
            int offset = (page.get()-1) * limit;
            sql += String.format(" LIMIT %d, %d", offset, limit);
        }
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, sql, null);
            while (resultSet.next()) {
                movies.add(
                        new Movie(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                resultSet.getString("image"),
                                resultSet.getInt("runtime"),
                                resultSet.getString("description"),
                                resultSet.getInt("director_id")
                                )
                            );
                        }
                        DBUtil.close(connection);
                        return movies;
                    } catch (DBConnectionException e) {
                        throw e;
                    } catch (SQLException e) {
                        throw new SQLStatmentException("SQL: " + sql);
                    }
                }

    @Override
    public Movie findByMovieId(int id){
        final String SQL = "SELECT * FROM movies WHERE id = ?";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection,SQL,List.of(id));
            if(resultSet.next()){
                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getString("image"),
                        resultSet.getInt("runtime"),
                        resultSet.getString("description"),
                        resultSet.getInt("director_id")
                );
            }else{
                return null;
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        final String SQL = "SELECT COUNT(*) FROM movies";
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
