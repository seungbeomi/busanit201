package kr.co.bnksys.todoapp.data.todo;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import kr.co.bnksys.todoapp.data.todo.local.TodoLocalDataSource;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.di.base.Local;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class TodoRepositoryImpl implements  TodoRepository {

    private final TodoLocalDataSource localDataSource;

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
    public TodoRepositoryImpl(@Local TodoLocalDataSource localDataSource) {
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
