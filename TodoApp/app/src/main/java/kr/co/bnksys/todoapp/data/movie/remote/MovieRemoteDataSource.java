package kr.co.bnksys.todoapp.data.movie.remote;

import java.util.List;

import io.reactivex.Maybe;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;

public interface MovieRemoteDataSource {
    Maybe<List<Movie>> fetchMovie();
}
