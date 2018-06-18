package br.com.globo.movies.movies.service;

import br.com.globo.movies.BuildConfig;
import br.com.globo.movies.service.ResponseObject;
import br.com.globo.movies.service.annotation.URL;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@URL(BuildConfig.API)
interface IMoviesListService {
    @GET("movies")
    Observable<ResponseObject> getMovies(@Query("limit") int limit, @Query("offset") int offset);
}
