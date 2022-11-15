/**
 * Title: MainActivity.java
 * Abstract: Using the layout from activity_main.xml for the GUI, implements a search book function
 *           and update function within the book database.
 *           If it fails to load the database it will add books and make a new database.
 *           It will add new books given the user knows the correct password and reports the time
 *           and date.
 * Author: Jalen Banks
 * ID: 1012
 * Date of Completion: 11/15/22
 */
package edu.csumb.bank1435.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import android.widget.LinearLayout;
import java.text.DateFormat;
import java.util.List;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout LL;
    private EditText titleText;
    private EditText hourlyText;
    private EditText passText;
    private Button searchButton;
    private Button updateButton;
    private TextView debugText;
    private BookDatabase bookDB;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LL = findViewById(R.id.mainLayout);
        titleText = findViewById(R.id.titleText);
        hourlyText = findViewById(R.id.rentalText);
        searchButton = findViewById(R.id.actionButton);
        debugText = findViewById(R.id.textView);
        passText = findViewById(R.id.passwordText);
        updateButton = findViewById(R.id.updateButton);

        searchButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        bookDB = BookDatabase.getDatabase(this);
        context = getApplicationContext();
        displayBooks();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.actionButton:
                search();
                break;

            case R.id.updateButton:
                update();
                break;

            default: break;
        }
        passText.setText("");
        // hinds keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(LL.getWindowToken(), 0);
    }

    void displayBooks()
    {
        List<Book> bookList = bookDB.getBookDao().getAll();
        if (bookList.size() <= 0) {
            Book[] defaultBook = new Book[5];
            defaultBook[0] = new Book("Absolute Java", 0.25);
            defaultBook[1] = new Book("Absolute Java 2", 0.50);
            defaultBook[2] = new Book("Python", 1.0);
            defaultBook[3] = new Book("Intro to CS", 0.25);
            defaultBook[4] = new Book("Hot Java", 0.05);
            bookDB.getBookDao().insert(defaultBook);
            bookList = bookDB.getBookDao().getAll();
        }

        String bookListText = "========== Book List ==========";
        for (Book book : bookList) {
            bookListText += "\n" + book.getTitle() + ", "
                    + book.getPriceFormatted();
        }
        debugText.setText(bookListText);
    }

    public boolean search()
    {
        String format = "###,##0.00";
        DecimalFormat df = new DecimalFormat(format);
        String target = titleText.getText().toString();
        List<Book> found = bookDB.getBookDao().searchbyTitle(target);
        if (found.size() <= 0)
        {
            Toast.makeText(context, "No book found", Toast.LENGTH_SHORT).show();
            return false;
        }
        int hoursRental = Integer.parseInt(hourlyText.getText().toString());
        double rentalPrice = found.get(0).getPrice() * hoursRental;
        String thing = "$" + df.format(rentalPrice);
        Toast.makeText(context, thing, Toast.LENGTH_LONG).show();
        return true;
    }

    public void update()
    {
        String passExtract = passText.getText().toString();
        String target = titleText.getText().toString();
        boolean newBook = true;
        if (passExtract.equals("Admin2"))
        {
            List<Book> currentBooks = bookDB.getBookDao().getAll();
            double updatedPrice = Double.parseDouble(hourlyText.getText().toString());
            String date = DateFormat.getDateTimeInstance().format(new Date());
            for (Book book : currentBooks)
            {
                if (book.getTitle().equals(target))
                {
                    book.setPrice(updatedPrice);
                    newBook = false;
                }
            }

            if (!newBook)
            {
                bookDB.getBookDao().update(currentBooks);

                Toast.makeText(context, "Book(s) updated at " + date, Toast.LENGTH_SHORT).show();
            }

            else
            {
                Book temp = new Book(target, updatedPrice);
                bookDB.getBookDao().insert(temp);
                Toast.makeText(context, "Book added at " + date, Toast.LENGTH_SHORT).show();
            }
            displayBooks();
        }

        else
        {
            Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
        }
    }
}