package kr.co.bnksys.todoapp.ui.movie;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kr.co.bnksys.todoapp.data.movie.MovieRepository;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.data.user.UserRepository;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;

@ActivityScoped
public class MoviePresenter implements MovieContract.Presenter {

    private final String TAG = this.getClass().getName();

    private MovieContract.View view;
    private MovieRepository repository;
    private CompositeDisposable disposable;

    @Inject
    public MoviePresenter(@Nullable MovieContract.View view,
                          @Nullable MovieRepository repository,
                          @Nullable CompositeDisposable disposable) {
        this.view = view;
        this.repository = repository;
        this.disposable = disposable;
    }

    @Override
    public void takeView(MovieContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    @Override
    public void fetchMovies() {
        repository.fetchMovies()
                .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<Movie>>() {
            @Override
            public void accept(List<Movie> movies) throws Exception {
                Log.d(TAG, "SUCCESS");
                view.showMovies(movies);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "FAIL");
            }
        });
    }
}
