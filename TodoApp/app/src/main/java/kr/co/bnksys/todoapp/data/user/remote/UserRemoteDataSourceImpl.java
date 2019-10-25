package kr.co.bnksys.todoapp.data.user.remote;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.user.remote.service.UserService;
import kr.co.bnksys.todoapp.data.user.remote.service.pojo.User;

@Singleton
public class UserRemoteDataSourceImpl implements UserRemoteDataSource {

    UserService userService;

    @Inject
    public UserRemoteDataSourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Single<User> login(@NonNull String email, @NonNull String password) {

        return userService.login(email, password)
                .map(userWrapperResponse -> {
                    return userWrapperResponse.getData();
                });
        /*
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
        */

    }
}
