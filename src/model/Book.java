package model;

import interfaces.Borrowable;
import interfaces.Returnable;
import exception.BookNotAvailableException;

public class Book extends LibraryItem implements Borrowable, Returnable {

    private String author;
    private String isbn;
    private boolean borrowed;

    public boolean isBorrowed() {
        return borrowed;
    }



    public Book(int id, String title, String author, String isbn, int year ) {

    super(id, title, year);
    this.author = author;
    this.isbn = isbn;

    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
  

    @Override 
    public void displayInfo() {

        System.out.println("ID : " + getId());
        System.out.println("Title : " + getTitle());
        System.out.println("Author : " + getAuthor());
        System.out.println("Isbn : " + getIsbn() );
        System.out.println(" year : " + getYear());
    }

    @Override 
    public void borrow() throws BookNotAvailableException{

        if (borrowed) {
            throw new BookNotAvailableException("Book is already borrowed");
        }
        borrowed = true;
        

    }

    @Override
    public void returnBook() {
        if (borrowed) {
            borrowed = false;
            System.out.println("Book returned successfully");
        }else {
            System.out.println("Book is not borrowed");
        }

    }

}


