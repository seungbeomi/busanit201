package kr.co.bnksys.todoapp.data.local;

import androidx.annotation.NonNull;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.Repository;
import kr.co.bnksys.todoapp.data.local.entity.Todo;
import kr.co.bnksys.todoapp.data.local.entity.User;

public interface LocalDataSource {

    // USER ------------------------------------
    void insertUser(@NonNull User user);

    // Single<User> login(@NonNull String email, @NonNull String password);

    // TODO ------------------------------------

    void saveTodo(@NonNull Todo todo);

    void getTodos(@NonNull Repository.LoadTodosCallback callback);

}
