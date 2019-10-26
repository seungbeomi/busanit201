package kr.co.bnksys.todoapp.data.movie.remote.service;

import java.util.List;

import io.reactivex.Maybe;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.WrapperMovie;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MovieService {

    @POST("api/v2/list_movies.json")
    Maybe<WrapperMovie> fetchMovies();

}
