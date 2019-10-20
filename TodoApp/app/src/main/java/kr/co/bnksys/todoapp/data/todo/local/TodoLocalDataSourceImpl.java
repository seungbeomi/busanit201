package kr.co.bnksys.todoapp.data.todo.local;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import kr.co.bnksys.todoapp.data.todo.TodoRepository;
import kr.co.bnksys.todoapp.data.todo.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class TodoLocalDataSourceImpl implements TodoLocalDataSource {

    private final TodoDao mTodoDao;
    private final AppExecutors mAppExecutors;

    @Inject
    public TodoLocalDataSourceImpl(@NonNull AppExecutors appExecutors,
                                   @NonNull TodoDao todoDao) {
        this.mTodoDao = todoDao;
        this.mAppExecutors = appExecutors;
    }

    @Override
    public void saveTodo(@NonNull Todo todo) {
        checkNotNull(todo);
        Runnable saveRunnable = () -> {
            mTodoDao.insertTodo(todo);
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void getTodos(@NonNull TodoRepository.LoadTodosCallback callback) {
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
