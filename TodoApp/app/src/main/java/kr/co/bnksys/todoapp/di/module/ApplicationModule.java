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
import kr.co.bnksys.todoapp.data.base.AppDatabase;
import kr.co.bnksys.todoapp.data.base.WrapperConverterFactory;
import kr.co.bnksys.todoapp.data.base.prefs.PreferencesManager;
import kr.co.bnksys.todoapp.data.base.prefs.PreferencesManagerImpl;
import kr.co.bnksys.todoapp.data.movie.MovieRepository;
import kr.co.bnksys.todoapp.data.movie.MovieRepositoryImpl;
import kr.co.bnksys.todoapp.data.movie.remote.MovieRemoteDataSource;
import kr.co.bnksys.todoapp.data.movie.remote.MovieRemoteDataSourceImpl;
import kr.co.bnksys.todoapp.data.movie.remote.service.MovieService;
import kr.co.bnksys.todoapp.data.todo.TodoRepository;
import kr.co.bnksys.todoapp.data.todo.TodoRepositoryImpl;
import kr.co.bnksys.todoapp.data.todo.local.TodoLocalDataSource;
import kr.co.bnksys.todoapp.data.todo.local.TodoLocalDataSourceImpl;
import kr.co.bnksys.todoapp.data.todo.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.user.UserRepository;
import kr.co.bnksys.todoapp.data.user.UserRepositoryImpl;
import kr.co.bnksys.todoapp.data.user.remote.UserRemoteDataSource;
import kr.co.bnksys.todoapp.data.user.remote.UserRemoteDataSourceImpl;
import kr.co.bnksys.todoapp.data.user.remote.service.UserService;
import kr.co.bnksys.todoapp.di.base.Local;
import kr.co.bnksys.todoapp.di.base.Remote;
import kr.co.bnksys.todoapp.util.AppExecutors;
import kr.co.bnksys.todoapp.util.DiskIOThreadExecutor;
import kr.co.bnksys.todoapp.util.rx.AppSchedulerProvider;
import kr.co.bnksys.todoapp.util.rx.SchedulerProvider;
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
    @Provides
    static AppDatabase provideAppDatabase(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }

    @Provides
    static SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    // 제공될때 신규로 생성해서 반환한다.
    @Provides
    static CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    static PreferencesManager providePreferencesManager(PreferencesManagerImpl preferencesManager) {
        return preferencesManager;
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(new WrapperConverterFactory(GsonConverterFactory.create())) // CUSTOM FACTORY
                .addConverterFactory(GsonConverterFactory.create()) // CUSTOM FACTORY
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        }).build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // REPOSITORY

    @Singleton
    @Binds
    abstract UserRepository provideUserRepository(UserRepositoryImpl dataSource);

    @Singleton
    @Binds
    abstract TodoRepository provideTodoRepository(TodoRepositoryImpl dataSource);

    @Singleton
    @Binds
    abstract MovieRepository provideMovieRepository(MovieRepositoryImpl dataSource);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DATASOURCE

    @Singleton
    @Binds
    @Local
    abstract TodoLocalDataSource provideTodoLocalDataSource(TodoLocalDataSourceImpl dataSource);

    @Singleton
    @Binds
    @Remote
    abstract UserRemoteDataSource provideUserRemoteDataSource(UserRemoteDataSourceImpl dataSource);

    @Singleton
    @Binds
    @Remote
    abstract MovieRemoteDataSource provideMovieRemoteDataSource(MovieRemoteDataSourceImpl dataSource);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DAO

    @Singleton
    @Provides
    static TodoDao provideTodoDao(AppDatabase db) {
        return db.todoDao();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // SERVICE

    @Singleton
    @Provides
    static UserService provideUserService() {
        return provideRetrofit(AppConstants.BASE_URL, provideClient()).create(UserService.class);
    }

    @Singleton
    @Provides
    static MovieService provideMovieService() {
        String movieBaseUrl = "https://yts.lt";
        return provideRetrofit(movieBaseUrl, provideClient()).create(MovieService.class);
    }

}
