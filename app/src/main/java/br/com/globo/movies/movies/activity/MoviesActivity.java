package br.com.globo.movies.movies.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.globo.movies.BaseActivity;
import br.com.globo.movies.R;
import br.com.globo.movies.movies.presenter.MoviesPresenter;
import br.com.globo.movies.movies.presenter.MoviesPresenterImpl;

@EActivity(R.layout.activity_movies)
public class MoviesActivity extends BaseActivity implements MoviesView, BottomNavigationView.OnNavigationItemSelectedListener {

    private MoviesPresenter mPresenter;

    @ViewById(R.id.navigation)
    BottomNavigationView navigation;

    @AfterViews
    void init(){
        mPresenter = new MoviesPresenterImpl();
        mPresenter.init(this);
        initListners();
    }

    private void initListners() {
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.action_movies);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return mPresenter.onNavigationItemSelected(item);
    }

    @Override
    public void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit();
    }
}
