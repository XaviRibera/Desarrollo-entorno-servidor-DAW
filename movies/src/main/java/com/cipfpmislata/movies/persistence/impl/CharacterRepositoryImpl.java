package com.cipfpmislata.movies.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cipfpmislata.movies.persistence.DAO.CharacterDAO;

@Repository
public class CharacterRepositoryImpl {
    
    @Autowired
    CharacterDAO characterDAO;
}
