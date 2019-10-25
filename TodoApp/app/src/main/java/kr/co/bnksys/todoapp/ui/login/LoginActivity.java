package kr.co.bnksys.todoapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.ui.base.BaseActivity;
import kr.co.bnksys.todoapp.ui.main.MainActivity;
import kr.co.bnksys.todoapp.ui.regist.RegistActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

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
                showLoading();
                presenter.login(email, password);
            } else {
                hideLoading();
                showSnackBar(getString(R.string.not_found_user));
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
        hideLoading();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
