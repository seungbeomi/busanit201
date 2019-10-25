package kr.co.bnksys.todoapp.ui.base;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import dagger.android.support.DaggerAppCompatActivity;
import kr.co.bnksys.todoapp.R;
import kr.co.bnksys.todoapp.util.ActivityUtils;

public class BaseActivity extends DaggerAppCompatActivity {

    private ProgressDialog progressDialog;

    public void showLoading() {
        hideLoading();
        progressDialog = ActivityUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

}
