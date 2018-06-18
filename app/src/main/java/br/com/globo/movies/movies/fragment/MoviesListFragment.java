package br.com.globo.movies.movies.fragment;

import android.support.v7.widget.GridLayoutManager;
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
import br.com.globo.movies.movies.adapter.MoviesAdapter;
import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.movies.presenter.MoviesListPresenter;
import br.com.globo.movies.movies.presenter.MoviesListPresenterImpl;

@EFragment(R.layout.fragment_movies_list)
public class MoviesListFragment extends BaseFragment implements MoviesListView, SwipyRefreshLayout.OnRefreshListener{
    private MoviesListPresenter mPresenter;

    @ViewById(R.id.movies)
    RecyclerView moviesView;

    @ViewById(R.id.swiperefresh)
    SwipyRefreshLayout swipeRefresh;

    private List<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @AfterViews
    void init(){
        mPresenter = new MoviesListPresenterImpl();
        mPresenter.init(this);
        initAdapter();
        mPresenter.loadMovies(20, 0);
    }

    private void initAdapter() {
        swipeRefresh.setOnRefreshListener( this );
        moviesAdapter = new MoviesAdapter(getActivity(), movies);
        moviesView.setAdapter( moviesAdapter );
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        moviesView.setLayoutManager( layoutManager );
    }

    @Override
    public void showToast(String message) {
        super.showToast(getActivity(), message );
    }

    @Override
    public void showMovies(List<Movie> movies) {
        this.movies.addAll( movies );
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        mPresenter.moreMovies();
        swipeRefresh.setRefreshing(false);
    }

    public void openDialog(){
        super.openDialog(getActivity());
    }

    @Override
    public void closeDialog(){
        super.closeDialog();
    }
}
