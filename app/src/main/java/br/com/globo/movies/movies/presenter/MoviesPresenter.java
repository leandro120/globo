package br.com.globo.movies.movies.presenter;

import android.view.MenuItem;

import br.com.globo.movies.movies.activity.MoviesActivity;
import br.com.globo.movies.movies.activity.MoviesView;

public interface MoviesPresenter {
    void init(MoviesView moviesView);

    boolean onNavigationItemSelected(MenuItem item);
}
