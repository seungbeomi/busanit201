package kr.co.bnksys.todoapp.data.user;

import androidx.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.data.user.remote.UserRemoteDataSource;
import kr.co.bnksys.todoapp.data.user.remote.service.pojo.User;
import kr.co.bnksys.todoapp.di.base.Remote;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final UserRemoteDataSource remoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<Long, Todo> mCachedTodos;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean isCacheDirty = false;

    @Inject
    public UserRepositoryImpl(@Remote UserRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Maybe<User> login(@NonNull String email, @NonNull String password) {
        // return localDataSource.login(email, password);
        return remoteDataSource.login(email, password);
    }

}
