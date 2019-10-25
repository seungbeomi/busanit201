package kr.co.bnksys.todoapp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import kr.co.bnksys.todoapp.di.module.AppModule;
import kr.co.bnksys.todoapp.di.module.ActivityModule;
import kr.co.bnksys.todoapp.di.module.ApplicationModule;

/**
 * App 과 동일한 디렉토리에 있어야함
 * error: cannot find symbol variable AppBaseComponent 발생함
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ApplicationModule.class,
        ActivityModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    // Gives us syntactic sugar. we can then do AppBaseComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
