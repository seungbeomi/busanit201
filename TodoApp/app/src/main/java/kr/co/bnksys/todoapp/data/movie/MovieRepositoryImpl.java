package kr.co.bnksys.todoapp.data.movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import kr.co.bnksys.todoapp.data.movie.remote.MovieRemoteDataSource;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.di.base.Remote;

@Singleton
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieRemoteDataSource remoteDataSource;

    @Inject
    public MovieRepositoryImpl(@Remote MovieRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Maybe<List<Movie>> fetchMovies() {
        return remoteDataSource.fetchMovie();
    }
}
