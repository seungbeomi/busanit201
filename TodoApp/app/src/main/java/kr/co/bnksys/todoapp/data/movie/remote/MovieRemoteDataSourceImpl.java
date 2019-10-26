package kr.co.bnksys.todoapp.data.movie.remote;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.functions.Function;
import kr.co.bnksys.todoapp.data.movie.remote.service.MovieService;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.WrapperMovie;

@Singleton
public class MovieRemoteDataSourceImpl implements MovieRemoteDataSource {

    private final String TAG = this.getClass().getName();

    MovieService movieService;

    @Inject
    public MovieRemoteDataSourceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Maybe<List<Movie>> fetchMovie() {
        return movieService.fetchMovies()
                .map(new Function<WrapperMovie, List<Movie>>() {
                    @Override
                    public List<Movie> apply(WrapperMovie wrapperMovie) throws Exception {
                        Log.d(TAG, "responseMovie " + wrapperMovie.toString());
                        return wrapperMovie.getData().getMovies();
                    }
                });
    }
}
