package kr.co.bnksys.todoapp.data.remote;

import androidx.annotation.NonNull;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.local.entity.User;

public interface RemoteDataSource {

    Single<User> login(@NonNull String email, @NonNull String password);

}
