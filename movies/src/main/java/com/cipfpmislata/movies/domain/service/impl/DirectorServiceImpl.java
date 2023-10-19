package com.cipfpmislata.movies.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.service.DirectorService;
import com.cipfpmislata.movies.exception.ResourceNotFoundException;
import com.cipfpmislata.movies.persistence.DirectorRepository;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<Director> getAll(Optional<Integer> page, Optional<Integer> page_size){
        return directorRepository.getAll(page, page_size);
    }

    @Override
    public Director findByDirectorId(int id){
        return directorRepository.findDirectorById(id);
    }

    @Override
    public int insert(Director director){
        return directorRepository.insert(director);
    }

    @Override
    public int getTotalNumberOfRecords(){
        return directorRepository.getTotalNumberOfRecords();
    }

    @Override
    public void update(Director director){
        Director existingDirector = directorRepository.findDirectorById(director.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + director.getId()));
        directorRepository.update(director);
    }
}
