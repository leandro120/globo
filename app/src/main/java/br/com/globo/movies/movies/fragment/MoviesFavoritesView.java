package br.com.globo.movies.movies.fragment;

import java.util.List;

import br.com.globo.movies.movies.model.Movie;

public interface MoviesFavoritesView {

    void showToast(int msg);

    void showMoviesFavorites(List<Movie> movies);

    void openDialog();

    void closeDialog();
}
