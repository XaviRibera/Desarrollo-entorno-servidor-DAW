package com.cipfpmislata.movies.controller;

import com.cipfpmislata.movies.domain.service.MovieService;
import com.cipfpmislata.movies.http_response.Response;
import com.cipfpmislata.movies.mapper.CharacterMapper;
import com.cipfpmislata.movies.mapper.MovieMapper;
import com.cipfpmislata.movies.domain.entity.Character;
import com.cipfpmislata.movies.domain.entity.Movie;
import com.cipfpmislata.movies.controller.model.character.CharacterCreateWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterDetailWeb;
import com.cipfpmislata.movies.controller.model.character.CharacterListWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieListWeb;
import com.cipfpmislata.movies.controller.model.movie.MovieUpdateWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Response findByMovieId(@PathVariable("id") int id) {
        MovieDetailWeb movieDetailWeb = MovieMapper.mapper.toMovieDetailWeb(movieService.findByMovieId(id));
        return Response.builder()
                .data(movieDetailWeb)
                .build();
    }

    @GetMapping("/search/{name}")
    public Response findByMovieName(@PathVariable("name") String name){
        MovieDetailWeb movieDetailWeb = MovieMapper.mapper.toMovieDetailWeb(movieService.findByMovieName(name));
        return Response.builder()
                .data(movieDetailWeb)
                .build();
    }

    @GetMapping("/{id}/characters")
    public Response getCharacterByMovieId(@PathVariable("id") int id){
        List<CharacterListWeb> charactersListWeb = new ArrayList<>();
        List<Character> characters = movieService.getCharacterByMovieId(id);
        for(Character character : characters){
            charactersListWeb.add(CharacterMapper.mapper.toCharacterListWeb(character));
        }
        return Response.builder()
                .data(charactersListWeb)
                .build();
    }

    @GetMapping("/{movieId}/characters/{characterId}")
    public Response findByCharacterId(@PathVariable("characterId") int id){
        CharacterDetailWeb characterDetailWeb = CharacterMapper.mapper.toCharacterDetailWeb(movieService.findByCharacterId(id));
        return Response.builder()
                .data(characterDetailWeb)
                .build();
    }

    @PostMapping("")
    public Response insertMovie(@RequestBody MovieCreateWeb movieCreateWeb){
        int id = movieService.insertMovie(MovieMapper.mapper.toMovie(movieCreateWeb));
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

    @PostMapping("/{id}/characters")
    public Response insertCharacter(@PathVariable("id") int movieId, @RequestBody List<CharacterCreateWeb> charactersCreateWeb){
        movieService.insertCharacter(CharacterMapper.mapper.toCharacters(charactersCreateWeb));
        return Response.builder()
                .data(MovieMapper.mapper.toMovieDetailWeb(movieService.findByMovieId(movieId)))
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") int movieId){
        movieService.deleteMovie(movieId);
    }

    @DeleteMapping("/{id}/characters")
    public void deleteCharacterByMovieId(@PathVariable("id") int movieId){
        movieService.deleteCharacterByMovieId(movieId);
    }

    @DeleteMapping("/{movieId}/characters/{characterId}")
    public void deleteCharacterById(@PathVariable("characterId") int characterId){
        movieService.deleteCharacterById(characterId);
    }

    @PutMapping("/{id}")
    public Response updateMovie(@PathVariable("id") int movieId,@RequestBody MovieUpdateWeb movieUpdateWeb){
        Movie movie = MovieMapper.mapper.toMovie(movieUpdateWeb);
        movie.setId(movieId);
        movieService.updateMovie(movie);
        return Response.builder().data(MovieMapper.mapper.toMovieDetailWeb(movie)).build();
    }
}
