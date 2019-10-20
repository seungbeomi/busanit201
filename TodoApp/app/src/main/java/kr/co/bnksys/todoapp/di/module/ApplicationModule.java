package kr.co.bnksys.todoapp.di.module;

import android.app.Application;

import androidx.room.Room;


import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import kr.co.bnksys.todoapp.AppConstants;
import kr.co.bnksys.todoapp.data.todo.TodoRepository;
import kr.co.bnksys.todoapp.data.todo.TodoRepositoryImpl;
import kr.co.bnksys.todoapp.data.todo.local.TodoLocalDataSource;
import kr.co.bnksys.todoapp.data.todo.local.TodoLocalDataSourceImpl;
import kr.co.bnksys.todoapp.data.user.UserRepository;
import kr.co.bnksys.todoapp.data.user.UserRepositoryImpl;
import kr.co.bnksys.todoapp.data.todo.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.user.remote.UserRemoteDataSource;
import kr.co.bnksys.todoapp.data.user.remote.UserRemoteDataSourceImpl;
import kr.co.bnksys.todoapp.data.base.AppDatabase;
import kr.co.bnksys.todoapp.di.base.Local;
import kr.co.bnksys.todoapp.di.base.Remote;
import kr.co.bnksys.todoapp.util.AppExecutors;
import kr.co.bnksys.todoapp.util.DiskIOThreadExecutor;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // REPOSITORY

    @Singleton
    @Binds
    abstract UserRepository provideUserRepository(UserRepositoryImpl dataSource);

    @Singleton
    @Binds
    abstract TodoRepository provideTodoRepository(TodoRepositoryImpl dataSource);

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // DAO

    @Singleton
    @Provides
    static TodoDao provideTodoDao(AppDatabase db) {
        return db.todoDao();
    }

}
