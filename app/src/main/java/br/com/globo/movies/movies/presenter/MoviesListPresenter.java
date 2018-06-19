package br.com.globo.movies.movies.presenter;

import br.com.globo.movies.movies.fragment.MoviesListFragment;
import br.com.globo.movies.movies.fragment.MoviesListView;
import br.com.globo.movies.movies.model.Movie;

public interface MoviesListPresenter {
    void init(MoviesListView moviesListView);

    void loadMovies(int limit, int offset);

    void moreMovies();

    void movieFavorite(Movie movie);

    Boolean checkIfFavorite(Movie movie);

    void loadMovies(int limit);
}
