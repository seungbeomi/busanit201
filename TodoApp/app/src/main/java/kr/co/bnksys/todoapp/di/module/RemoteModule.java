package kr.co.bnksys.todoapp.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.data.user.remote.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class RemoteModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        }).build();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // SERVICE

    @Singleton
    @Provides
    static UserService provideUserService() {
        return provideRetrofit(AppConstants.BASE_URL, provideClient()).create(UserService.class);
    }

}
