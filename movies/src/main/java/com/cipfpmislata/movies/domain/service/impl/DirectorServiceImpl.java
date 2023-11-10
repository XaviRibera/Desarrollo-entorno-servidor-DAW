package com.cipfpmislata.movies.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cipfpmislata.movies.domain.entity.Director;
import com.cipfpmislata.movies.domain.persistance.DirectorRepository;
import com.cipfpmislata.movies.domain.service.DirectorService;
import com.cipfpmislata.movies.exception.ResourceNotFoundException;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<Director> getAll(Integer page, Integer page_size){
        return directorRepository.getAll(page, page_size);
    }

    @Override
    public Director findByDirectorId(int id){
        return directorRepository.findDirectorById(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
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
        directorRepository.findDirectorById(director.getId()).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + director.getId()));
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        directorRepository.findDirectorById(id).orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con id: " + id));
        directorRepository.delete(id);
    }
}
