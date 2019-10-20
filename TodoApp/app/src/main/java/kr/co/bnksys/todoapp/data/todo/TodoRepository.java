package kr.co.bnksys.todoapp.data.todo;

import androidx.annotation.NonNull;

import java.util.List;

import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;

public interface TodoRepository {

    interface LoadTodosCallback {

        void onTodosLoaded(List<Todo> todos);

        void onDataNotAvailable();
    }

    void getTodos(@NonNull LoadTodosCallback callback);

    void refreshTodos();

    void saveTodo(@NonNull Todo todo);

}
