package kr.co.bnksys.todoapp.data;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.local.entity.Todo;
import kr.co.bnksys.todoapp.data.local.entity.User;

public interface Repository {

    Single<User> login(String email, String password);

    interface LoadTodosCallback {

        void onTodosLoaded(List<Todo> todos);

        void onDataNotAvailable();
    }

    void getTodos(@NonNull LoadTodosCallback callback);

    void refreshTodos();

    void saveTodo(@NonNull Todo todo);

}
