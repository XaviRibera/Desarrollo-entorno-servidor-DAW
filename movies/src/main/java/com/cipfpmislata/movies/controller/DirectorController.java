package com.cipfpmislata.movies.controller;

import java.util.List;

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

import com.cipfpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.service.DirectorService;
import com.cipfpmislata.movies.http_response.Response;
import com.cipfpmislata.movies.mapper.DirectorMapper;

@RequestMapping("/directors")
@RestController
public class DirectorController {
    @Autowired
    private DirectorService directorService;
    
    @Value("${LIMIT}") 
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize: LIMIT;
        int totalRecords = directorService.getTotalNumberOfRecords();

        List<Director> directors = (page != null)?directorService.getAll(pageSize, page) : directorService.getAll(null, null);
        List<DirectorListWeb> directorsWeb = directors.stream()
                    .map(director -> DirectorMapper.mapper.toDirectorListWeb(director))
                    .toList();

            Response response = Response.builder()
                .data(directorsWeb)
                .totalRecords(totalRecords)
                .build();
            return response;
    }

    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        DirectorDetailWeb directorDetailWeb = DirectorMapper.mapper.toDirectorDetailWeb(directorService.findByDirectorId(id));
        return Response.builder()
                .data(directorDetailWeb)
                .build();
    }

    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response insert(@RequestBody DirectorCreateWeb directorCreateWeb){
        int id = directorService.insert(DirectorMapper.mapper.toDirector(directorCreateWeb));
        DirectorDetailWeb directorDetailWeb = new DirectorDetailWeb(
            id,
            directorCreateWeb.getName(),
            directorCreateWeb.getBirthYear(),
            directorCreateWeb.getDeathYear()
    );
    
    return Response.builder().data(directorDetailWeb).build();
    }
    
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody DirectorUpdateWeb directorUpdateWeb) {
        Director director = DirectorMapper.mapper.toDirector(directorUpdateWeb);
        director.setId(id);
        directorService.update(director);
    }

    //----------El Delete de Director no se podria sin hacer validadiones----------\\
    /*
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }
    */
}
