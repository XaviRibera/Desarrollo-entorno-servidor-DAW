package com.cipfpmislata.movies.controller;

import java.util.List;
import java.util.Optional;

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

import com.cipfpmislata.movies.controller.model.director.DirectorListWeb;
import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.service.DirectorService;
import com.cipfpmislata.movies.http_response.Response;
import com.cipfpmislata.movies.mapper.director.DirectorMapper;

@RequestMapping("/directors")
@RestController
public class DirectorController {
    @Autowired
    private DirectorService directorService;
    
    @Value("${LIMIT}") 
    private int LIMIT;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        int limit = (pageSize.isPresent())? pageSize.get(): LIMIT;
        int totalRecords = directorService.getTotalNumberOfRecords();

        List<Director> directors = directorService.getAll(pageSize, page);
        List<DirectorListWeb> directorWeb = directors.stream()
                    .map(director -> DirectorMapper.mapper.toDirectorListWeb(director))
                    .toList();

        Response response = new Response(directorWeb, totalRecords, page, limit);

        return response;
    }

    @GetMapping("/{id}")
    public Optional<Director> find(@PathVariable("id") int id) {
        try {
            System.out.println(directorService.findByDirectorId(id));
            return directorService.findByDirectorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Director insert(@RequestBody Director director){
        int id = directorService.insert(director);
        director.setId(id);
        return director;
    }
    
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Director director) {
        director.setId(id);
        directorService.update(director);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }
}
