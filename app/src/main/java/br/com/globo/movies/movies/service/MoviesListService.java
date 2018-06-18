package br.com.globo.movies.movies.service;

import java.util.List;

import br.com.globo.movies.movies.model.Movie;
import rx.Observable;

public interface MoviesListService {
    Observable<List<Movie>> getMovies(int limit, int offset);
}
