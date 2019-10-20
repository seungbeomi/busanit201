package kr.co.bnksys.todoapp.di.module.ui;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;
import kr.co.bnksys.todoapp.ui.main.MainActivity;
import kr.co.bnksys.todoapp.ui.main.MainContract;
import kr.co.bnksys.todoapp.ui.main.MainPresenter;

@Module
public abstract class MainModule {

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter presenter);

    @ActivityScoped
    @Binds
    abstract MainContract.View mainView(MainActivity mainView);

}
