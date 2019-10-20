package kr.co.bnksys.todoapp.data.user.remote.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    // @Field parameters can only be used with form encoding.
    @FormUrlEncoded
    @POST("users/auth")
    Call<String> login(@Field("email") String email, @Field("password") String password);

}
