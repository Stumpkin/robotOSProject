package edu.csumb.ybyun.booklist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addButton, searchButton, deleteButton, updateButton, exercise1Button, exercise2Button;
    private EditText authorText, titleText, isbnText, yearText;

    private BookDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authorText = findViewById(R.id.author);
        titleText = findViewById(R.id.title);
        isbnText = findViewById(R.id.isbn);
        yearText = findViewById(R.id.year);

        addButton = findViewById(R.id.add);
        deleteButton = findViewById(R.id.delete);
        searchButton = findViewById(R.id.search);
        updateButton = findViewById(R.id.update);
        exercise1Button = findViewById(R.id.exercise1);
        exercise2Button = findViewById(R.id.exercise2);

        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        exercise1Button.setOnClickListener(this);
        exercise2Button.setOnClickListener(this);

        db = BookDatabase.getDatabase(this);

        displayBookList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                addBook();
                break;

            case R.id.search:
                searchBooksByAuthor();
                break;

            case R.id.delete:
                deleteBook();
                break;

            case R.id.update:
                updateBook();
                break;

            case R.id.exercise1:
                exercise1();
                break;

            case R.id.exercise2:
                exercise2();
                break;

            default:
                break;
        }
    }

    private void displayBookList() {
        List<Book> bookList = db.getBookDao().getAll();

        // If there is no data, add five records as an example.
        if (bookList.size() <= 0) {
            Book[] defaultBook = new Book[5];
            defaultBook[0] = new Book("Alice", "Absolute Java", "12-345", 2022, 0.25);
            defaultBook[1] = new Book("Bob", "Absolute Java 2", "22-222", 1975, 0.50);
            defaultBook[2] = new Book("Chris", "Python", "77-777", 2020, 1.0);
            defaultBook[3] = new Book("Eric", "Intro to CS", "33-333", 2019, 0.25);
            defaultBook[4] = new Book("Alice", "Hot Java", "12-333", 1999, 0.05);
            db.getBookDao().insert(defaultBook);

            // Read the inserted default book data to display them on the screen
            bookList = db.getBookDao().getAll();
        }

        String bookListText = "========== Book List ==========";
        for (Book book : bookList) {
            bookListText += "\n" + book.getId() + ", "
                    + book.getAuthor() + ", "
                    + book.getTitle() + ", "
                    + book.getIsbn() + ", "
                    + book.getYear();
        }

        Toast.makeText(this, bookListText, Toast.LENGTH_LONG).show();

        // Reset (= clear) the input text fields
        authorText.setText("");
        titleText.setText("");
        isbnText.setText("");
        yearText.setText("");
    }

    public void addBook() {
        Book book = new Book();
        book.setAuthor(authorText.getText().toString());
        book.setTitle(titleText.getText().toString());
        book.setIsbn(isbnText.getText().toString());
        //book.setYear(Integer.parseInt(yearText.getText().toString()));
        // Check for an empty year.
        if(!yearText.getText().toString().equals("")) {
            book.setYear(Integer.parseInt(yearText.getText().toString()));
        }

        db.getBookDao().insert(book);
        displayBookList();
    }

    public void searchBooksByAuthor() {
        String author = authorText.getText().toString();
        List<Book> bookList = db.getBookDao().searchByName(author);
        String bookListText = "========== Search Result ==========";
        for (Book book : bookList) {
            bookListText += "\n" + book.getId() + ", "
                    + book.getAuthor() + ", "
                    + book.getTitle() + ", "
                    + book.getIsbn() + ", "
                    + book.getYear();
        }

        Toast.makeText(this, bookListText, Toast.LENGTH_SHORT).show();
    }

    public void deleteBook() {
        String name = authorText.getText().toString();
        db.getBookDao().deleteByName(name);
        displayBookList();
    }

    public void updateBook() {
        String isbn = isbnText.getText().toString();

        List<Book> bookList = db.getBookDao().searchByIsbn(isbn);

        if (bookList.size() == 0) {
            Toast.makeText(this, "Error: No book with the ISBN", Toast.LENGTH_SHORT).show();
        }
        else if (bookList.size() > 1) {
            Toast.makeText(this, "Error: Multiple books with the same ISBN", Toast.LENGTH_SHORT).show();
        }
        else {
            Book book = bookList.get(0);
            book.setAuthor(authorText.getText().toString());
            book.setTitle(titleText.getText().toString());
            book.setIsbn(isbn);
            if(!yearText.getText().toString().equals("")) {
                book.setYear(Integer.parseInt(yearText.getText().toString()));
            }

            db.getBookDao().update(book);
            displayBookList();
        }
    }

    public void exercise1() {
        AlertDialog.Builder developAlert = new AlertDialog.Builder(this);

        developAlert.setMessage("Will develop soon...");

        developAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // We don't really need to do anything here.
                    }
                });
        developAlert.create().show();
    }

    public void exercise2() {
        AlertDialog.Builder developAlert = new AlertDialog.Builder(this);

        developAlert.setMessage("Will develop soon...");

        developAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // We don't really need to do anything here.
                    }
                });
        developAlert.create().show();
    }
}

