package br.com.globo.movies.movies.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import br.com.globo.movies.BaseFragment;
import br.com.globo.movies.R;
import br.com.globo.movies.movies.adapter.MoviesFavoritesAdapter;
import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.movies.presenter.MoviesFavoritesPresenter;
import br.com.globo.movies.movies.presenter.MoviesFavoritesPresenterImpl;

@EFragment(R.layout.fragment_movies_favorites)
public class MoviesFavoritesFragment extends BaseFragment implements MoviesFavoritesView{
    @ViewById(R.id.movies)
    RecyclerView moviesFavoritesView;

    private MoviesFavoritesAdapter moviesFavoritesAdapter;
    private MoviesFavoritesPresenter mPresenter;

    @AfterViews
    void init(){
        mPresenter = new MoviesFavoritesPresenterImpl();
        mPresenter.init(this);
        mPresenter.loadMoviesFavorites();
    }

    @Override
    public void showToast(int msg) {
        super.showToast(getActivity(), msg );
    }

    @Override
    public void showMoviesFavorites(List<Movie> movies) {
        moviesFavoritesAdapter = new MoviesFavoritesAdapter(getActivity(), movies, mPresenter);
        moviesFavoritesView.setAdapter( moviesFavoritesAdapter );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        moviesFavoritesView.setLayoutManager( layoutManager );
    }

    @Override
    public void openDialog(){
        super.openDialog(getActivity());
    }

    @Override
    public void closeDialog(){
        super.closeDialog();
    }
}
