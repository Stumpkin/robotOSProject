/**
 * Title: BookDao.java
 * Abstract: An interface where Dao uses auto generated functions to implement
 * Author: Jalen Banks
 * ID: 1012
 * Date of Completion: 11/13/22
 */
package edu.csumb.bank1435.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface BookDao
{
    @Insert
    void insert(Book... books);

    @Update
    void update(Book book);

    @Update
    void update(List<Book> books);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM Books")
    List<Book> getAll();

    @Query("SELECT * FROM Books Where title = :title")
    List<Book> searchbyTitle(String title);

}
