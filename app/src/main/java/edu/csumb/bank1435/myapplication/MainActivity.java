// TODO: Figure out what part 2 is
// TODO: Recongure Book to only have the title and price (you can keep the id number)
package edu.csumb.bank1435.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleText;
    private EditText hourlyText;
    private Button searchButton;
    private TextView debugText;
    private BookDatabase bookDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleText);
        hourlyText = findViewById(R.id.rentalText);
        searchButton = findViewById(R.id.actionButton);
        debugText = findViewById(R.id.textView);

        searchButton.setOnClickListener(this);
        bookDB = BookDatabase.getDatabase(this);
        displayBooks();
    }

    @Override
    public void onClick(View view)
    {
        //do something...
        search();
    }

    void displayBooks()
    {
        List<Book> bookList = bookDB.getBookDao().getAll();
        if (bookList.size() <= 0) {
            Book[] defaultBook = new Book[5];
            defaultBook[0] = new Book("Alice", "Absolute Java", "12-345", 2022, 0.25);
            defaultBook[1] = new Book("Bob", "Absolute Java 2", "22-222", 1975, 0.50);
            defaultBook[2] = new Book("Chris", "Python", "77-777", 2020, 1.0);
            defaultBook[3] = new Book("Eric", "Intro to CS", "33-333", 2019, 0.25);
            defaultBook[4] = new Book("Alice", "Hot Java", "12-333", 1999, 0.05);
            bookDB.getBookDao().insert(defaultBook);
            bookList = bookDB.getBookDao().getAll();
        }

        String bookListText = "========== Book List ==========";
        for (Book book : bookList) {
            bookListText += "\n" + book.getId() + ", "
                    + book.getAuthor() + ", "
                    + book.getTitle() + ", "
                    + book.getIsbn() + ", "
                    + book.getYear() + ", "
                    + book.getPriceFormatted();
        }
        debugText.setText(bookListText);
    }

    public void search()
    {
        String format = "###,##0.00";
        DecimalFormat df = new DecimalFormat(format);
        String target = titleText.getText().toString();
        List<Book> found = bookDB.getBookDao().searchbyTitle(target);
        if (found.size() <= 0)
        {
            debugText.setText("No book found");
            return;
        }
        int hoursRental = Integer.parseInt(hourlyText.getText().toString());
        double rentalPrice = found.get(0).getPrice() * hoursRental;
        String thing = "$" + df.format(rentalPrice);
        debugText.setText(thing);

    }
}