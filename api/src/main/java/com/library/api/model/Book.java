package com.library.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity 
@Table (name = "books")
public class Book {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private int year;
    private String isbn;
    private boolean borrowed;

    public int getID() {
        return id;
    }

    public String getTitle() {
    return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public Book() {

    }

    public void setTitle(String title) {
    this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
        

}
