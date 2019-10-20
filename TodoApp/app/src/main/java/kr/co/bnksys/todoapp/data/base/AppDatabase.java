package kr.co.bnksys.todoapp.data.base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kr.co.bnksys.todoapp.data.todo.local.dao.TodoDao;
import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;

@Database(entities = { Todo.class }, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

}