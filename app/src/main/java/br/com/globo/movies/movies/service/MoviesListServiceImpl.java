package br.com.globo.movies.movies.service;

import java.util.List;

import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.service.CheckResponseStatus;
import br.com.globo.movies.service.ServiceFactory;
import rx.Observable;

public class MoviesListServiceImpl implements MoviesListService {
    @Override
    public Observable<List<Movie>> getMovies(int limit, int offset) {
        IMoviesListService service = ServiceFactory.createRetrofitService( IMoviesListService.class );

        return service.getMovies(limit, offset)
                .lift(new CheckResponseStatus())
                .map(response -> {
                    if (response != null && response.getResponse() != null) {
                        return response.getResponse().getResult().getMovies();
                    }
                    return null;
                });
    }
}
