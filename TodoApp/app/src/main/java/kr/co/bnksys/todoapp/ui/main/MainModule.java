package kr.co.bnksys.todoapp.ui.main;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.ActivityScoped;

@Module
public abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);

    @ActivityScoped
    @Binds
    abstract MainContract.View mainView(MainActivity mainView);

}
