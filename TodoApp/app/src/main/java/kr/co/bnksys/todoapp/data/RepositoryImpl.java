package kr.co.bnksys.todoapp.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.local.LocalDataSource;
import kr.co.bnksys.todoapp.data.local.entity.Todo;
import kr.co.bnksys.todoapp.data.local.entity.User;
import kr.co.bnksys.todoapp.data.remote.RemoteDataSource;
import kr.co.bnksys.todoapp.di.Local;
import kr.co.bnksys.todoapp.di.Remote;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class RepositoryImpl implements Repository {

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;

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
    public RepositoryImpl(@Remote RemoteDataSource remoteDataSource,
                          @Local LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void saveTodo(@NonNull Todo todo) {
        checkNotNull(todo);
        localDataSource.saveTodo(todo);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedTodos == null) {
            mCachedTodos = new LinkedHashMap<>();
        }
        mCachedTodos.put(todo.getId(), todo);
    }

    @Override
    public void refreshTodos() {
        isCacheDirty = true;
    }

    @Override
    public Single<User> login(@NonNull String email, @NonNull String password) {
        return localDataSource.login(email, password);
    }

    @Override
    public void getTodos(@NonNull final LoadTodosCallback callback) {
        if (mCachedTodos != null && !isCacheDirty) {
            callback.onTodosLoaded(new ArrayList<Todo>(mCachedTodos.values()));
            return;
        }

        localDataSource.getTodos(new LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<Todo> todos) {
                refreshCache(todos);
                callback.onTodosLoaded(new ArrayList<Todo>(mCachedTodos.values()));
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void refreshCache(List<Todo> todos) {
        if (mCachedTodos == null) {
            mCachedTodos = new LinkedHashMap<>();
        }
        mCachedTodos.clear();
        for (Todo todo : todos) {
            mCachedTodos.put(todo.getId(), todo);
        }
        isCacheDirty = false;
    }

}
