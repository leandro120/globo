package br.com.globo.movies.movies.presenter;

import br.com.globo.movies.movies.fragment.MoviesFavoritesView;
import br.com.globo.movies.movies.model.Movie;

public interface MoviesFavoritesPresenter {
    void init(MoviesFavoritesView moviesFavoritesView);

    void loadMoviesFavorites();

    void removeFavorite(Movie movie);
}
