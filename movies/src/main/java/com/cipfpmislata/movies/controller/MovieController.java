package com.cipfpmislata.movies.controller;

import com.cipfpmislata.movies.domain.service.MovieService;
import com.cipfpmislata.movies.http_response.Response;
import com.cipfpmislata.movies.mapper.MovieMapper;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.controller.model.character.CharacterCreateWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieListWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(MovieController.MOVIES)
@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Value("${application.url}")
    private String urlBase;

    public static final String MOVIES = "/movies";
    
    @Value("${LIMIT}")
    private int LIMIT;
    

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : LIMIT;
        
        List<Movie> movies = (page != null)?movieService.getAll(page, pageSize) : movieService.getAll(null,null);
        List<MovieListWeb> moviesWeb = movies.stream()
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))
                .toList();
        int totalRecords = movieService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(moviesWeb)
                .totalRecords(totalRecords)
                .build();
        return response;
    }

    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        MovieDetailWeb movieDetailWeb = MovieMapper.mapper.toMovieDetailWeb(movieService.findByMovieId(id));
        return Response.builder()
                .data(movieDetailWeb)
                .build();
    }

    @PostMapping("")
    public Response insert(@RequestBody MovieCreateWeb movieCreateWeb){
        int id = movieService.insert(MovieMapper.mapper.toMovie(movieCreateWeb));
        List<CharacterCreateWeb> charactersCreateWeb = movieCreateWeb.getCharactersCreateWeb();
        List<CharacterListWeb> charactersListWeb = new ArrayList<>();
        for(CharacterCreateWeb characterCreateWeb : charactersCreateWeb){
            charactersListWeb.add( new CharacterListWeb(
                characterCreateWeb.getCharacterName(),
                characterCreateWeb.getActorListWeb()
            ));
        }
        MovieDetailWeb movieDetailWeb = new MovieDetailWeb(
            id,
            movieCreateWeb.getTitle(),
            movieCreateWeb.getYear(),
            movieCreateWeb.getImage(),
            movieCreateWeb.getRuntime(),
            movieCreateWeb.getDirectorListWeb(),
            charactersListWeb
        );
        return Response.builder().data(movieDetailWeb).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int movieId){
        movieService.delete(movieId);
    }
}
