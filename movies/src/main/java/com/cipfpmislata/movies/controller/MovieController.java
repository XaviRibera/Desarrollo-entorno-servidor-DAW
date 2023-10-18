package com.cipfpmislata.movies.controller;

import com.cipfpmislata.movies.domain.service.MovieService;
import com.cipfpmislata.movies.http_response.Response;

import com.cipfpmislata.movies.domain.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    
    @Value("${LIMIT}")
    private int LIMIT;
    

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        int limit = (pageSize.isPresent())? pageSize.get(): LIMIT;
        int totalRecords = movieService.getTotalNumberOfRecords();

        Response response = new Response(movieService.getAll(page, Optional.of(limit)), totalRecords, page, limit);

        return response;
    }

    @GetMapping("/{id}")
    public Movie find(@PathVariable("id") int id) {
        try {
            System.out.println(movieService.findByMovieId(id));
            return movieService.findByMovieId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
