package edu.csumb.bank1435.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;

@Entity(tableName = "Books")
public class Book
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String author;
    private String title;
    private String isbn;
    private int year;
    private double price;
    private String priceFormatted;

    public Book()
    {
        price = 0;
        priceFormatted = "$0.00";
    }

    public Book(String a, String t, String is, int y, double p)
    {
        this.price = p;
        this.author = a;
        this.title = t;
        this.isbn = is;
        this.year = y;
        this.priceFormatted = setPriceFormat();
    }

    String setPriceFormat()
    {
        String format = "###,##0.00";
        DecimalFormat df = new DecimalFormat(format);
        return "$" + df.format(price);
    }

    public int getId() { return this.id; }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public int getYear() {
        return this.year;
    }

    public double getPrice() { return this.price; }

    public String getPriceFormatted() { return this.priceFormatted; }

    public void setPriceFormatted(String thing) { this.priceFormatted = thing; }

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
