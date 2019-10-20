package kr.co.bnksys.todoapp.data.todo.local;

import androidx.annotation.NonNull;

import kr.co.bnksys.todoapp.data.todo.TodoRepository;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;

public interface TodoLocalDataSource {

    void saveTodo(@NonNull Todo todo);

    void getTodos(@NonNull TodoRepository.LoadTodosCallback callback);

}
