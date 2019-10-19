package kr.co.bnksys.todoapp.data.local;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.Repository;
import kr.co.bnksys.todoapp.data.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.local.dao.UserDao;
import kr.co.bnksys.todoapp.data.local.entity.Todo;
import kr.co.bnksys.todoapp.data.local.entity.User;
import kr.co.bnksys.todoapp.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class LocalDataSourceImpl implements LocalDataSource {

    private final TodoDao mTodoDao;
    private final UserDao mUserDao;
    private final AppExecutors mAppExecutors;

    @Inject
    public LocalDataSourceImpl(@NonNull AppExecutors appExecutors,
                               @NonNull UserDao userDao,
                               @NonNull TodoDao todoDao) {
        this.mTodoDao = todoDao;
        this.mUserDao = userDao;
        this.mAppExecutors = appExecutors;
    }

    @Override
    public void insertUser(@NonNull User user) {

    }

    /*
    @Override
    public Single<User> login(@NonNull String email, @NonNull String password) {
        return mUserDao.login(email, password);
    }
    */

    @Override
    public void saveTodo(@NonNull Todo todo) {
        checkNotNull(todo);
        Runnable saveRunnable = () -> {
            mTodoDao.insertTodo(todo);
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void getTodos(@NonNull Repository.LoadTodosCallback callback) {
        Runnable runnable = () -> {
            final List<Todo> todos = mTodoDao.getTodos();
            mAppExecutors.mainThread().execute(() -> {
                if (todos.isEmpty()) {
                    // This will be called if the table is new or just empty.
                    callback.onDataNotAvailable();
                } else {
                    callback.onTodosLoaded(todos);
                }
            });
        };

        mAppExecutors.diskIO().execute(runnable);
    }

}
