package kr.co.bnksys.todoapp.di.module;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kr.co.bnksys.todoapp.data.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.local.dao.UserDao;
import kr.co.bnksys.todoapp.data.local.entity.Todo;
import kr.co.bnksys.todoapp.data.local.entity.User;

@Database(entities = { Todo.class, User.class }, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

    public abstract UserDao userDao();

}