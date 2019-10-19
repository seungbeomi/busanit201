package kr.co.bnksys.todoapp.ui.regist;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.ActivityScoped;

@Module
public abstract class RegistModule {

    @ActivityScoped
    @Binds
    abstract RegistContract.Presenter mainPresenter(RegistPresenter presenter);

    @ActivityScoped
    @Binds
    abstract RegistContract.View mainView(RegistActivity mainView);

}
