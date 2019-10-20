package kr.co.bnksys.todoapp.data.todo.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kr.co.bnksys.todoapp.data.todo.local.entity.Todo;

@Dao
public interface TodoDao {

    /**
     * Insert a task in the database. If the todo already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTodo(Todo todo);

    @Update
    int updateTodo(Todo todo);

    @Query("SELECT * FROM todos ORDER BY id DESC")
    List<Todo> getTodos();
}
