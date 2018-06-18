package br.com.globo.movies.movies.presenter;

import br.com.globo.movies.movies.fragment.MoviesListFragment;
import br.com.globo.movies.movies.fragment.MoviesListView;

public interface MoviesListPresenter {
    void init(MoviesListView moviesListView);

    void loadMovies(int limit, int offset);

    void moreMovies();
}
