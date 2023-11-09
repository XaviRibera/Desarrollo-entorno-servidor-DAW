package com.cipfpmislata.movies.controller;

import com.cipfpmislata.movies.domain.service.MovieService;
import com.cipfpmislata.movies.http_response.Response;
import com.cipfpmislata.movies.mapper.MovieMapper;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieListWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;
        int totalRecords = movieService.getTotalNumberOfRecords();

        List<Movie> movies = (page != null)?movieService.getAll(page, pageSize) : movieService.getAll(null,null);
        List<MovieListWeb> movieWeb = movies.stream()
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))
                .toList();

        Response response = new Response(movieWeb, totalRecords, page, pageSize);

        return response;
    }

    @GetMapping("/{id}")
    public MovieDetailWeb find(@PathVariable("id") int id) {
        return  MovieMapper.mapper.toMovieDetailWeb(movieService.findByMovieId(id));
    }
}
