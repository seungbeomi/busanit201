package kr.co.bnksys.todoapp.data.user.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.data.user.remote.service.UserService;
import kr.co.bnksys.todoapp.data.user.remote.service.pojo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRemoteDataSourceImpl implements UserRemoteDataSource {

    UserService userService;

    @Inject
    public UserRemoteDataSourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Single<User> login(@NonNull String email, @NonNull String password) {
        Call<String> response = userService.login(email, password);
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(AppConstants.TAG, "onResponse");
                
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(AppConstants.TAG, "onFailure");

            }
        });

        //return userService.login(email, password);
        return Single.just(new User());
    }
}
