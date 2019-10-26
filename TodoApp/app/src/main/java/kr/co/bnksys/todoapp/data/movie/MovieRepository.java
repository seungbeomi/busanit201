package kr.co.bnksys.todoapp.data.movie;

import java.util.List;

import io.reactivex.Maybe;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;

public interface MovieRepository {

    Maybe<List<Movie>> fetchMovies();

}
