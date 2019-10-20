package kr.co.bnksys.todoapp.di.module.ui;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;
import kr.co.bnksys.todoapp.ui.regist.RegistActivity;
import kr.co.bnksys.todoapp.ui.regist.RegistContract;
import kr.co.bnksys.todoapp.ui.regist.RegistPresenter;

@Module
public abstract class RegistModule {

    @ActivityScoped
    @Binds
    abstract RegistContract.Presenter mainPresenter(RegistPresenter presenter);

    @ActivityScoped
    @Binds
    abstract RegistContract.View mainView(RegistActivity mainView);

}
