package br.com.globo.movies.userProfile.fragment;

import android.opengl.Visibility;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import br.com.globo.movies.BaseFragment;
import br.com.globo.movies.R;
import br.com.globo.movies.userProfile.model.User;
import br.com.globo.movies.userProfile.presenter.UserProfilePresenter;
import br.com.globo.movies.userProfile.presenter.UserProfilePresenterImpl;

@EFragment(R.layout.fragment_user_profile)
public class UserProfileFragment extends BaseFragment implements UserProfileView {
    UserProfilePresenter mPresenter;

    @ViewById(R.id.email)
    EditText email;

    @ViewById(R.id.password)
    EditText password;

    @ViewById(R.id.login_form)
    RelativeLayout loginForm;

    @ViewById(R.id.user_profile)
    RelativeLayout userProfile;

    @ViewById(R.id.image)
    ImageView image;

    @ViewById(R.id.name)
    TextView name;

    @ViewById(R.id.age)
    TextView age;

    @ViewById(R.id.bio)
    TextView bio;

    @AfterViews
    void init(){
        mPresenter = new UserProfilePresenterImpl();
        mPresenter.init(this);
        mPresenter.checkIfLogged();
    }

    @Click(R.id.login)
    void login(){
        mPresenter.login(email.getText().toString(), password.getText().toString());
    }

    @Click(R.id.logout)
    void logout(){
        mPresenter.logout();
    }

    public void openDialog(){
        super.openDialog(getActivity());
    }

    @Override
    public void closeDialog(){
        super.closeDialog();
    }

    @Override
    public void showToast(String msg) {
        super.showToast(getActivity(), msg );
    }

    @Override
    public void showUser(User user) {
        loginForm.setVisibility(View.GONE);
        userProfile.setVisibility(View.VISIBLE);

        Picasso.get().load(user.getImages().get(0).getLarge())
                .into(image);
        name.setText(user.getName());
        age.setText(user.getAge());
        bio.setText(user.getBio());
    }

    @Override
    public void showLoginForm() {
        userProfile.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);
    }
}
