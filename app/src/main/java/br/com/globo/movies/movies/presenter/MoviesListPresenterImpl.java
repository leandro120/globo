package br.com.globo.movies.movies.presenter;

import java.util.List;

import br.com.globo.movies.movies.fragment.MoviesListView;
import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.movies.service.MoviesListService;
import br.com.globo.movies.movies.service.MoviesListServiceImpl;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviesListPresenterImpl implements MoviesListPresenter {
    private MoviesListView mView;
    private MoviesListService mService;
    private int OFFSET =0;

    @Override
    public void init(MoviesListView moviesListView) {
        this.mView = moviesListView;
        this.mService = new MoviesListServiceImpl();
    }

    @Override
    public void loadMovies(int limit, int offset) {
        mView.openDialog();

        mService.getMovies(limit, offset)
                .subscribeOn( Schedulers.newThread())
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe(this::successMoviesResponse, this::errorHandle);
    }

    @Override
    public void moreMovies() {
        OFFSET = OFFSET+20;
        this.loadMovies( 20, OFFSET);
    }

    private void errorHandle(Throwable throwable) {
        mView.closeDialog();
        mView.showToast(throwable.getMessage());
    }

    private void successMoviesResponse(List<Movie> movies) {
        mView.closeDialog();
        mView.showMovies(movies);
    }
}
