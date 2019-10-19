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
import kr.co.bnksys.todoapp.data.Repository;
import kr.co.bnksys.todoapp.data.RepositoryImpl;
import kr.co.bnksys.todoapp.data.local.LocalDataSource;
import kr.co.bnksys.todoapp.data.local.LocalDataSourceImpl;
import kr.co.bnksys.todoapp.data.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.local.dao.UserDao;
import kr.co.bnksys.todoapp.data.remote.RemoteDataSource;
import kr.co.bnksys.todoapp.data.remote.RemoteDataSourceImpl;
import kr.co.bnksys.todoapp.di.Local;
import kr.co.bnksys.todoapp.di.Remote;
import kr.co.bnksys.todoapp.util.AppExecutors;
import kr.co.bnksys.todoapp.util.DiskIOThreadExecutor;

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
}
