package kr.co.bnksys.todoapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.ui.base.BaseActivity;

/**
 * View 담당
 *
 * 1. 사용자 인터렉션 담당
 * 2. 입력값검증
 * 3. DTO -> Entity 변환
 * 4. Presenter 호출
 * 5. Entity -> DTO 변환
 * 6. 결과값 설정
 */
public class MainActivity extends BaseActivity implements MainContract.View {

    private final String TAG = this.getClass().getName();

    private MainRecyclerViewAdapter adapter;

    @Inject
    MainContract.Presenter presenter;

    @BindView(R.id.todo)
    EditText mTodo;

    //@BindView(R.id.todolist)
    //RecyclerView recyclerView;

    @OnClick(R.id.btnAdd)
    void onAdd() {
        String todo = mTodo.getText().toString();
        if (todo == null || "".equals(todo)) {
            return;
        }

        Log.d(TAG, todo);
        presenter.saveTodo(todo);
    }

    @Inject
    public MainActivity() {
        // Requires empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // RecyclerView 초기화
        initRecyclerView();
        // 데이터 조회
        presenter.loadTodos();
    }

    private void initRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.todolist) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        adapter = new MainRecyclerViewAdapter(new ArrayList<Todo>(0), presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // prevent leaking activity in case presenter is orchestrating a long running task
        presenter.dropView();
    }

    /** TODO리스트 표시 */
    @Override
    public void showTodoList() {
        mTodo.setText("");
        mTodo.clearFocus();
        Snackbar.make(mTodo, getString(R.string.successfully_saved_todo_message), Snackbar.LENGTH_LONG).show();

        // 데이터 조회
        presenter.loadTodos();
    }

    @Override
    public void showTodos(List<Todo> todos) {
        runOnUiThread(() -> {
            adapter.replaceData(todos);
        });
    }

}
