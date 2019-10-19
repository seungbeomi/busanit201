package kr.co.bnksys.todoapp.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kr.co.bnksys.todoapp.di.ActivityScoped;
import kr.co.bnksys.todoapp.ui.login.LoginActivity;
import kr.co.bnksys.todoapp.ui.login.LoginModule;
import kr.co.bnksys.todoapp.ui.main.MainActivity;
import kr.co.bnksys.todoapp.ui.main.MainModule;
import kr.co.bnksys.todoapp.ui.regist.RegistActivity;
import kr.co.bnksys.todoapp.ui.regist.RegistModule;

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
}
