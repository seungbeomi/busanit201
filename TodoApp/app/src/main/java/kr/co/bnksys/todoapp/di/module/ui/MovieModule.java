package kr.co.bnksys.todoapp.di.module.ui;

import dagger.Binds;
import dagger.Module;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;
import kr.co.bnksys.todoapp.ui.main.MainContract;
import kr.co.bnksys.todoapp.ui.movie.MovieActivity;
import kr.co.bnksys.todoapp.ui.movie.MovieContract;
import kr.co.bnksys.todoapp.ui.movie.MoviePresenter;

@Module
public abstract class MovieModule {

    @ActivityScoped
    @Binds
    abstract MovieContract.Presenter moviePresenter(MoviePresenter presenter);

    @ActivityScoped
    @Binds
    abstract MovieContract.View movieView(MovieActivity view);

}
