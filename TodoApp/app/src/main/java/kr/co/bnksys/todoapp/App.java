package kr.co.bnksys.todoapp;

import android.util.Log;

import com.facebook.stetho.Stetho;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

    private final String TAG = this.getClass().getName();

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            debugConfig();
        }
    }

    private void debugConfig() {
        Log.d(TAG, "::: DEBUG MODE :::");
        Stetho.initializeWithDefaults(this);
    }
}
