package br.com.globo.movies.userProfile.service;


import br.com.globo.movies.userProfile.model.User;
import rx.Observable;

public interface UserService {
    Observable<User> login(String email, String password );
}
