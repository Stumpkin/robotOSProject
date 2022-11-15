/**
 * Title: BookDatabase.java
 * Abstract: A implementation of a database for the books using a singleton design pattern
 * Author: Jalen Banks
 * ID: 1012
 * Date of Completion: 11/13/22
 */
package edu.csumb.bank1435.myapplication;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase
{
    public abstract BookDao getBookDao();
    private static BookDatabase oneTime;

    static BookDatabase getDatabase(Context context)
    {
        if (oneTime == null)
        {
            oneTime = Room.databaseBuilder(context.getApplicationContext(),
                    BookDatabase.class, "Book.DB").allowMainThreadQueries().build();
        }
        return oneTime;
    }
}
