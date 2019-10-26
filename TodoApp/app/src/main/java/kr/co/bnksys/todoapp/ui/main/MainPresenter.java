package kr.co.bnksys.todoapp.ui.main;

import android.util.Log;

import androidx.annotation.Nullable;


import java.util.List;

import javax.inject.Inject;

import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.data.todo.TodoRepository;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.di.base.ActivityScoped;

/**
 * Model과 View사이의 매개체
 * 서버의 Service 와 같이 사용
 *
 * 비즈니스 로직 Entity 반환
 */
@ActivityScoped
public class MainPresenter implements MainContract.Presenter {

    private final String TAG = this.getClass().getName();
    private final TodoRepository repository;
    private MainContract.View mainView;

    @Inject
    public MainPresenter(@Nullable MainContract.View mainView,
                         @Nullable TodoRepository repository) {
        this.mainView = mainView;
        this.repository = repository;
    }

    @Nullable
    private Long mTodoId;

    @Override
    public void takeView(MainContract.View view) {
        mainView = view;
    }

    @Override
    public void dropView() {
        mainView = null;
    }

    @Override
    public void saveTodo(String todo) {
        Todo t = new Todo();
        t.setTodo(todo);
        t.setCompleted(false);

        repository.saveTodo(t);
        if (mainView != null) {
            mainView.showTodoList();
        }
    }

    @Override
    public void showDetail(Long id) {

    }

    @Override
    public void loadTodos() {
        repository.refreshTodos();
        repository.getTodos(new TodoRepository.LoadTodosCallback() {
            @Override
            public void onTodosLoaded(List<Todo> todos) {
                Log.d(TAG, "onTodosLoaded");
                mainView.showTodos(todos);
            }
            @Override
            public void onDataNotAvailable() {
                Log.d(TAG, "onDataNotAvailable");
            }
        });
    }

}
