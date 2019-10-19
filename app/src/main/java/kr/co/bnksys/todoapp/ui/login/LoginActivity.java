package kr.co.bnksys.todoapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.arch.core.util.Function;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.ui.main.MainActivity;
import kr.co.bnksys.todoapp.ui.regist.RegistActivity;

public class LoginActivity extends DaggerAppCompatActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

    @Inject
    public LoginActivity() {

    }

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvPassword)
    TextView tvPassword;

    @OnClick(R.id.btnLogin)
    void onLogin() {
        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();

        // 입력값검증
        Observable.combineLatest(Observable.just(email)
                , Observable.just(password)
                , (a, b) -> !TextUtils.isEmpty(a) && !TextUtils.isEmpty(b))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation()).subscribe(o -> {
            if (o) {
                presenter.login(email, password);
            } else {
                Snackbar.make(tvEmail, getString(R.string.not_found_user), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btnRegist)
    void onRegist() {
        Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

    }

    @Override
    public void goMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
