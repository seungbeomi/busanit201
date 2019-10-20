package kr.co.bnksys.todoapp.di.module.ui;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;
import kr.co.bnksys.todoapp.ui.login.LoginActivity;
import kr.co.bnksys.todoapp.ui.login.LoginContract;
import kr.co.bnksys.todoapp.ui.login.LoginPresenter;

@Module
public abstract class LoginModule {

    @ActivityScoped
    @Binds
    abstract LoginContract.Presenter loginPresenter(LoginPresenter presenter);

    @ActivityScoped
    @Binds
    abstract LoginContract.View loginView(LoginActivity loginView);

}
