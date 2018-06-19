package br.com.globo.movies.userProfile.presenter;

import br.com.globo.movies.userProfile.fragment.UserProfileView;
import br.com.globo.movies.userProfile.model.User;
import br.com.globo.movies.userProfile.service.UserProfileService;
import br.com.globo.movies.userProfile.service.UserProfileServiceImpl;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserProfilePresenterImpl implements UserProfilePresenter {
    private UserProfileView mView;
    private UserProfileService mService;
    private Realm realm;

    @Override
    public void init(UserProfileView userProfileView) {
        this.mView = userProfileView;
        this.mService = new UserProfileServiceImpl();
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void checkIfLogged(){
        User userResult = realm.where(User.class).findFirst();
        if(userResult != null){
            mView.showUser(userResult);
        }else{
            mView.showLoginForm();
        }
    }

    @Override
    public void login(String email, String password){
        mView.openDialog();

        mService.login(email, password)
                .subscribeOn( Schedulers.newThread())
                .observeOn( AndroidSchedulers.mainThread())
                .subscribe(this::successLoginResponse, this::errorHandle);
    }

    @Override
    public void logout() {
        realm.executeTransactionAsync(realm -> {
            User userResult = realm.where(User.class).findFirst();
            if(userResult != null){
                userResult.deleteFromRealm();
            }
        });
        mView.showLoginForm();
    }

    private void successLoginResponse(User user) {
        mView.closeDialog();
        realm.executeTransactionAsync(realm -> realm.copyToRealm(user));
        mView.showUser(user);
    }

    private void errorHandle(Throwable throwable) {
        mView.closeDialog();
        mView.showToast(throwable.getMessage());
    }
}
