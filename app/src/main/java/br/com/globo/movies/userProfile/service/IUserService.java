package br.com.globo.movies.userProfile.service;


import br.com.globo.movies.BuildConfig;
import br.com.globo.movies.service.ResponseObject;
import br.com.globo.movies.service.annotation.URL;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

@URL(BuildConfig.API)
public interface IUserService {
    @POST("login")
    Observable<ResponseObject> login(@Body String email, @Body String password);
}
