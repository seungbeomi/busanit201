package kr.co.bnksys.todoapp.ui.main;

import java.util.List;

import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.ui.base.BasePresenter;
import kr.co.bnksys.todoapp.ui.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showTodoList();

        void showTodos(List<Todo> todos);
    }

    interface Presenter extends BasePresenter<View> {

        /** TODO 저장 */
        void saveTodo(String todo);

        /** TODO 상세 */
        void showDetail(Long id);

        /** TODO 목록조회 */
        void loadTodos();
    }

}
