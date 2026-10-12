package service;

import model.Book;
import dao.BookDAO;
import exception.BookNotAvailableException;
import java.util.ArrayList;


public class Library {

   private ArrayList<Book> books = new ArrayList<>();


 // add book
   public void addBook(Book book) {

    //books.add(book);
    BookDAO.addBook(book);

   }

   public void showBooks() {
    ArrayList<Book> books = BookDAO.getAllBooks();
    System.out.println("Books found: " + books.size());
    for (Book book : books) {
         book.displayInfo();
    }
   }

   //find by id
   public Book findBookByID (int id) {

    return BookDAO.getBookByID(id);

   }

   //find by tittle
   public ArrayList<Book> findBookByTitle(String title) {
    return BookDAO.searchByTitle(title);
   }


   //remove book
    
    public void removeBookById(int id) {
        Book book = BookDAO.getBookByID(id);

        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        if (book.isBorrowed()) {
            System.out.println("Cannot remove a borrowed book");
            return;
        }

        boolean deleted = BookDAO.removeBookById(id);

        if (deleted) {
            System.out.println("Book removed successfully");
        } else {
            System.out.println("Could not remove book");
        }
    }


   //borrow 
   public void borrowBookById (int id, int memberID) {

        Book book = BookDAO.getBookByID(id);

        if(book == null) {
            System.out.println("Book not found");
            return ;
        }

        if (book.isBorrowed()) {
            System.out.println("Book is already Borrowed");
            return;      
        }

        boolean update = BookDAO.borrowedBookById(id, memberID);

        if (update) {
            try {
                book.borrow();
                System.out.println("Book borrowed successfully");
            } catch (BookNotAvailableException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Could not borrow book");
        }
    }
   

    //return method
    public void returnById (int id, int memberID) {

        Book book = BookDAO.getBookByID(id);

        if (book == null) {
            System.out.println(" Book not found");
            return;
        }

        if (!book.isBorrowed()) {
            System.out.println("Book is not currently borrowed");
            return;
        }

        boolean update = BookDAO.returnBookById(id, memberID);

        if (update) {
            book.returnBook();
            System.out.println("Book return successfully");
        }else {
            System.out.println("Couldnot return Book");
        }
    }

    //show available books
    public  void showAvailableBooks() {

        ArrayList<Book> books = BookDAO.getAllBooks();

        for (Book book : books) {
            if (!book.isBorrowed()) {
                book.displayInfo();
            }
        }
    }

    //show borrowed books 
    public void showBorrowedBooks() {

        ArrayList<Book> books = BookDAO.getAllBooks();

        for (Book book : books) {
            if (book.isBorrowed()) {
                book.displayInfo();
            }
        }
    }




    


}

