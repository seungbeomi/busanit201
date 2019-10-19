package kr.co.bnksys.todoapp.ui.login;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.data.Repository;
import kr.co.bnksys.todoapp.di.ActivityScoped;

@ActivityScoped
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private Repository repository;

    @Inject
    public LoginPresenter(@Nullable LoginContract.View view,
                          @Nullable Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void login(String email, String password) {

        /*
        presenter.login(email, password)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Snackbar.make(getWindow().getDecorView().getRootView(),getString(R.string.not_found_user), Snackbar.LENGTH_LONG).show();
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u -> {
                    Log.d(AppConstants.TAG, "사용자정보");
                })
            ;
         */

        // repository.login(email, password);

        view.goMain();
    }
}
