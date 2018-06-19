package br.com.globo.movies.userProfile.fragment;

import br.com.globo.movies.userProfile.model.User;

public interface UserProfileView {

    void openDialog();

    void closeDialog();

    void showToast(String message);

    void showUser(User user);

    void showLoginForm();
}
