package kr.co.bnksys.todoapp.data.user;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.user.remote.service.pojo.User;

public interface UserRepository {

    Single<User> login(String email, String password);

}
