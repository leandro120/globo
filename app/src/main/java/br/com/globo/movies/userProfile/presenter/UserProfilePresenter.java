package br.com.globo.movies.userProfile.presenter;

import br.com.globo.movies.userProfile.fragment.UserProfileView;

public interface UserProfilePresenter {
    void init(UserProfileView userProfileView);

    void checkIfLogged();

    void login(String email, String password);

    void logout();
}
