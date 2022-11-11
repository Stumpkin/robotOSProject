package edu.csumb.ybyun.booklist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao getBookDao();

    private static BookDatabase dbInstance;

    static BookDatabase getDatabase(Context context) {
        if(dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    BookDatabase.class,
                    "Book.DB").allowMainThreadQueries().build();
        }
        return dbInstance;
    }
}

