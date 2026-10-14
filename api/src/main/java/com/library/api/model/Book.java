package com.library.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity 
@Table (name = "books")
public class Book {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title is required")
    @Size(max = 150, message = "Title cannot exceed 150 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 100,message = "Author cannot exceed 100 characters")
    private String author;

    @Positive (message = "Year must be greater than 0")
    private int year;

    @Size (max = 20, message = "ISBN cannot exceed 20 charcter")
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
