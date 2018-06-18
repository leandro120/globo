package br.com.globo.movies.movies.fragment;

import java.util.List;

import br.com.globo.movies.movies.model.Movie;

public interface MoviesListView {
    void showToast(String message);

    void showMovies(List<Movie> movies);
}
