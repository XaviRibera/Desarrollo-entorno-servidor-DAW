package com.cipfpmislata.movies.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.service.DirectorService;
import com.cipfpmislata.movies.http_response.Response;

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

        Response response = new Response(directorService.getAll(page, Optional.of(limit)), totalRecords, page, limit);

        return response;
    }

    @GetMapping("/{id}")
    public Director find(@PathVariable("id") int id) {
        try {
            System.out.println(directorService.findByDirectorId(id));
            return directorService.findByDirectorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    
    @PostMapping("")
    public void insert(@RequestBody Director director){
            directorService.insert(director);
    }
    
}
