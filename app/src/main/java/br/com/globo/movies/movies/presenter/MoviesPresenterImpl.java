package br.com.globo.movies.movies.presenter;

import android.view.MenuItem;

import br.com.globo.movies.R;
import br.com.globo.movies.movies.activity.MoviesView;
import br.com.globo.movies.movies.fragment.MoviesFavoritesFragment;
import br.com.globo.movies.movies.fragment.MoviesFavoritesFragment_;
import br.com.globo.movies.movies.fragment.MoviesListFragment_;

public class MoviesPresenterImpl implements MoviesPresenter {
    private MoviesView mView;

    @Override
    public void init(MoviesView moviesView) {
        this.mView = moviesView;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_movies:
                mView.showFragment(MoviesListFragment_.builder().build());
                return true;
            case R.id.action_favorites:
                mView.showFragment(MoviesFavoritesFragment_.builder().build());
                return true;
            case R.id.action_profile:
                return true;
        }
        return false;
    }
}
