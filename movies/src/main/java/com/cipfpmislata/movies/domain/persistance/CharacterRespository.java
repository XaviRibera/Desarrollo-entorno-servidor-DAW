package com.cipfpmislata.movies.domain.persistance;

import java.util.List;

import com.cipfpmislata.movies.domain.entity.Character;

public interface CharacterRespository {
    public List<Character> findByMovieId(int movieId);
}
