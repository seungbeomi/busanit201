package kr.co.bnksys.todoapp.di.module;

import android.app.Application;

import androidx.room.Room;


import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.BuildConfig;
import kr.co.bnksys.todoapp.data.Repository;
import kr.co.bnksys.todoapp.data.RepositoryImpl;
import kr.co.bnksys.todoapp.data.local.LocalDataSource;
import kr.co.bnksys.todoapp.data.local.LocalDataSourceImpl;
import kr.co.bnksys.todoapp.data.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.local.dao.UserDao;
import kr.co.bnksys.todoapp.data.remote.RemoteDataSource;
import kr.co.bnksys.todoapp.data.remote.RemoteDataSourceImpl;
import kr.co.bnksys.todoapp.data.remote.service.UserService;
import kr.co.bnksys.todoapp.di.Local;
import kr.co.bnksys.todoapp.di.Remote;
import kr.co.bnksys.todoapp.util.AppExecutors;
import kr.co.bnksys.todoapp.util.DiskIOThreadExecutor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ApplicationModule {

    private static final int THREAD_COUNT = 3;

    @Singleton
    @Binds
    @Local
    abstract LocalDataSource provideLocalDataSource(LocalDataSourceImpl dataSource);

    @Singleton
    @Binds
    @Remote
    abstract RemoteDataSource provideRemoteDataSource(RemoteDataSourceImpl dataSource);

    @Singleton
    @Binds
    abstract Repository provideRepository(RepositoryImpl dataSource);

    @Singleton
    @Provides
    static AppDatabase provideTodoDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    static TodoDao provideTodoDao(AppDatabase db) {
        return db.todoDao();
    }

    @Singleton
    @Provides
    static UserDao provideUserDao(AppDatabase db) {
        return db.userDao();
    }

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }

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
    static UserService provideUserService() {
        return provideRetrofit(AppConstants.BASE_URL, provideClient()).create(UserService.class);
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

}
