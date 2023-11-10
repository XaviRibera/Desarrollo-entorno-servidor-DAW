package com.cipfpmislata.movies.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {
 
    private int id;
    private String title;
    private int year;
    private String image;
    private int runtime;
    private String description;
}