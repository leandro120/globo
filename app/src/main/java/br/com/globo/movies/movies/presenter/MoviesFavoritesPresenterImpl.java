package br.com.globo.movies.movies.presenter;

import br.com.globo.movies.R;
import br.com.globo.movies.movies.fragment.MoviesFavoritesView;
import br.com.globo.movies.movies.model.Movie;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MoviesFavoritesPresenterImpl implements MoviesFavoritesPresenter {
    private int OFFSET;
    private MoviesFavoritesView mView;
    private Realm realm;

    @Override
    public void init(MoviesFavoritesView moviesFavoritesView) {
        this.mView = moviesFavoritesView;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadMoviesFavorites() {
        mView.openDialog();

        RealmQuery<Movie> query = realm.where(Movie.class);
        RealmResults<Movie> movies = query.findAll();

        if(movies.size() == 0){
            mView.showToast(R.string.no_favorite);
        }

        mView.closeDialog();
        mView.showMoviesFavorites(movies);
    }

    @Override
    public void removeFavorite(Movie movie) {
        realm.beginTransaction();
        if(checkIfFavorite(movie)){
            Movie movieResult = realm.where(Movie.class).equalTo("id", movie.getId()).findFirst();
            movieResult.deleteFromRealm();
        }
        realm.commitTransaction();
    }

    private Boolean checkIfFavorite(Movie movie){
        Movie movieResult = realm.where(Movie.class).equalTo("id", movie.getId()).findFirst();
        return movieResult != null;
    }
}
