package kr.co.bnksys.todoapp.ui.regist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

import kr.co.bnksys.todoapp.R;

public class RegistActivity extends AppCompatActivity implements RegistContract.View  {

    @Inject
    public RegistActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }
}
