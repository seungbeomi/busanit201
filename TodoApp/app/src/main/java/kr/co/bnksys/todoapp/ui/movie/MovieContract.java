package kr.co.bnksys.todoapp.ui.movie;

import java.util.List;

import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.ui.base.BasePresenter;
import kr.co.bnksys.todoapp.ui.base.BaseView;

public interface MovieContract {

    interface View extends BaseView<Presenter> {

        void showMovies(List<Movie> movies);
    }

    interface Presenter extends BasePresenter<View> {

        void fetchMovies();
    }

}
