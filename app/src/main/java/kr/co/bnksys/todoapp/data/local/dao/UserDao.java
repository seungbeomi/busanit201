package kr.co.bnksys.todoapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;
import kr.co.bnksys.todoapp.data.local.entity.Todo;
import kr.co.bnksys.todoapp.data.local.entity.User;

@Dao
public interface UserDao {

    /**
     * Insert a task in the database. If the todo already exists, replace it.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    Single<User> login(String email, String password);

}
