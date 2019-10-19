package kr.co.bnksys.todoapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.arch.core.util.Function;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;
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

        Snackbar.make(tvEmail, getString(R.string.not_found_user), Snackbar.LENGTH_LONG).show();

        String email = tvEmail.getText().toString();
        String password = tvPassword.getText().toString();

        // 입력값검증

        presenter.login(email, password);
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
