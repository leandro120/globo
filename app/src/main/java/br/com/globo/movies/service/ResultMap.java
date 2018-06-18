package br.com.globo.movies.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.globo.movies.movies.model.Movie;

public class ResultMap {
    @SerializedName("movies")
    private List<Movie> movies;

}