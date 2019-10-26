package kr.co.bnksys.todoapp.ui.movie;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.data.movie.remote.service.pojo.Movie;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;
import kr.co.bnksys.todoapp.ui.base.BaseActivity;
import kr.co.bnksys.todoapp.ui.login.LoginContract;
import kr.co.bnksys.todoapp.ui.main.MainContract;
import kr.co.bnksys.todoapp.ui.main.MainRecyclerViewAdapter;

public class MovieActivity extends BaseActivity implements MovieContract.View  {

    private final String TAG = this.getClass().getName();

    private MovieRecyclerViewAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    MovieContract.Presenter presenter;

    @Inject
    public MovieActivity() {
        // Requires empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        // RecyclerView 초기화
        initRecyclerView();

        showLoading();
        presenter.fetchMovies();
    }

    private void initRecyclerView() {
        adapter = new MovieRecyclerViewAdapter(new ArrayList<Movie>(0), presenter);

        // RecyclerView recyclerView = findViewById(R.id.recyclerView) ;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)) ;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        runOnUiThread(() -> {
            adapter.replaceData(movies);
            hideLoading();
        });
    }
}
