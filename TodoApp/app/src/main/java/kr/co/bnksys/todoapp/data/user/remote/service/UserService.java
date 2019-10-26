package kr.co.bnksys.todoapp.data.user.remote.service;

import io.reactivex.Maybe;
import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.base.WrapperResponse;
import kr.co.bnksys.todoapp.data.user.remote.service.pojo.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    // @Field parameters can only be used with form encoding.
    @FormUrlEncoded
    @POST("users/auth")
    //Call<String> login(@Field("email") String email, @Field("password") String password);
    //Single<WrapperResponse<User>> login(@Field("email") String email, @Field("password") String password);
    Maybe<User> login(@Field("email") String email, @Field("password") String password);

}
