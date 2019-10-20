package kr.co.bnksys.todoapp.data.user.remote;

import androidx.annotation.NonNull;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.user.remote.service.pojo.User;

public interface UserRemoteDataSource {

    Single<User> login(@NonNull String email, @NonNull String password);

}
