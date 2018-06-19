package br.com.globo.movies.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.userProfile.model.User;

public class ResultMap {
    @SerializedName("movies")
    private List<Movie> movies;

    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}