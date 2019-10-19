package kr.co.bnksys.todoapp.ui.login;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.ActivityScoped;

@Module
public abstract class LoginModule {

    @ActivityScoped
    @Binds
    abstract LoginContract.Presenter loginPresenter(LoginPresenter presenter);

    @ActivityScoped
    @Binds
    abstract LoginContract.View loginView(LoginActivity loginView);

}
