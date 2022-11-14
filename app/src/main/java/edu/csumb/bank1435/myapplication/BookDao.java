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

    @Query("SELECT * FROM Books WHERE author = :name  ORDER BY  isbn ASC")
    List<Book> searchByName(String name);

    @Query("SELECT * FROM Books WHERE isbn = :isbn")
    List<Book> searchByIsbn(String isbn);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM Books WHERE author = :name")
    void deleteByName(String name);

    @Query("SELECT * FROM Books")
    List<Book> getAll();

    @Query("SELECT * FROM Books Where title = :title")
    List<Book> searchbyTitle(String title);

}
