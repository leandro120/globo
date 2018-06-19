package br.com.globo.movies.userProfile.service;

import br.com.globo.movies.service.CheckResponseStatus;
import br.com.globo.movies.service.ServiceFactory;
import br.com.globo.movies.userProfile.model.User;
import rx.Observable;

public class UserServiceImpl implements UserService {
    @Override
    public Observable<User> login(String email, String password) {
        IUserService service = ServiceFactory.createRetrofitService( IUserService.class );

        return service.login(email, password)
                .lift(new CheckResponseStatus())
                .map(response -> {
                    if (response != null && response.getResponse() != null) {
                        return response.getResponse().getResult().getUser();
                    }
                    return null;
                });
    }
}
