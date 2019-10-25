package kr.co.bnksys.todoapp.ui.login;

import android.util.Log;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.data.user.UserRepository;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;

@ActivityScoped
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private UserRepository repository;
    private CompositeDisposable disposable;

    @Inject
    public LoginPresenter(@Nullable LoginContract.View view,
                          @Nullable UserRepository repository,
                          @Nullable CompositeDisposable disposable) {
        this.view = view;
        this.repository = repository;
        this.disposable = disposable;
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
        disposable.clear();
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

        disposable.add(repository.login(email, password)
            .subscribeOn(Schedulers.io())
            .doOnError(throwable -> System.out.println("doOnError: " + throwable))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((user, throwable) -> {
                Log.d(AppConstants.TAG, user.getName());
                view.goMain();
            }));
    }

}
