package com.esplay.entity;
/*
 * Created by nvishwarupe
 */

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String id;
    private int version;
    private String title;
    private int score;
    private List<String> genres;
    private int year;
}


