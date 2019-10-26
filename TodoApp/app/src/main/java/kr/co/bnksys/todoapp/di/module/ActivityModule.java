package kr.co.bnksys.todoapp.di.module;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;
import kr.co.bnksys.todoapp.di.module.ui.LoginModule;
import kr.co.bnksys.todoapp.di.module.ui.MovieModule;
import kr.co.bnksys.todoapp.ui.login.LoginActivity;
import kr.co.bnksys.todoapp.ui.login.LoginContract;
import kr.co.bnksys.todoapp.ui.login.LoginPresenter;
import kr.co.bnksys.todoapp.ui.main.MainActivity;
import kr.co.bnksys.todoapp.di.module.ui.MainModule;
import kr.co.bnksys.todoapp.ui.main.MainContract;
import kr.co.bnksys.todoapp.ui.main.MainPresenter;
import kr.co.bnksys.todoapp.ui.movie.MovieActivity;
import kr.co.bnksys.todoapp.ui.regist.RegistActivity;
import kr.co.bnksys.todoapp.di.module.ui.RegistModule;
import kr.co.bnksys.todoapp.ui.regist.RegistContract;
import kr.co.bnksys.todoapp.ui.regist.RegistPresenter;

@Module
public abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RegistModule.class)
    abstract RegistActivity registActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MovieModule.class)
    abstract MovieActivity movieActivity();

}
