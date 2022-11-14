package edu.csumb.ybyun.booklist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String author;
    private String title;
    private String isbn;
    private int year;
    private double price;
    //private String priceFormatted;

    public Book ()
    {
        price = 0;
        // default constructor - no initialization.
    }

    public Book (String author, String title, String isbn, int year, double price)
    {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() { return price; }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) { this.price = price; }
}

