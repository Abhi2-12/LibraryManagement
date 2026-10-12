package dao;

import model.Book;
import exception.BookNotAvailableException;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class BookDAO {

    //add book
    public static void addBook(Book book) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO books (title, author, year, isbn) VALUES (?, ?, ?, ?)"
             ))
        {
            
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getYear());
            statement.setString(4, book.getIsbn());

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Book added successfully");
            }

        

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    //method for show books
    public static ArrayList<Book> getAllBooks() {

        ArrayList<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
            "SELECT * From books");
            ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int year = resultSet.getInt("year");
                boolean borrowed = resultSet.getBoolean("borrowed");

                Book book = new Book(id, title, author, isbn, year);

                if (borrowed) {
                    try {
                        book.borrow();
                    } catch (BookNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                }

                books.add(book);

            }

        } catch (SQLException e) {
            e.getMessage();
        }

        return books;
   
    }

    //Search a book by id
    public static Book getBookByID(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM books where id = ? "
        
    )) {

        statement.setInt(1, id);
        ResultSet resultSet =statement.executeQuery();

        if (resultSet.next()) {

            int bookId  = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            int year = resultSet.getInt("year");

            boolean borrowed = resultSet.getBoolean("borrowed");

            Book book = new Book(bookId , title, author, isbn, year);

            if (borrowed) {
                try {
                    book.borrow();
                } catch (BookNotAvailableException e) {
                    System.out.println(e.getMessage());
                }
            }

            return book;
        }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //search by tittle
    public static ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> books = new ArrayList<>();

        //create sql qurary
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
        "SELECT * FROM books where title LIKE ?") ){

            statement.setString(1, "%" +  title + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                int year = resultSet.getInt("year");
                
                
                boolean borrowed = resultSet.getBoolean("borrowed");

                Book foundBook = new Book(id, bookTitle, author, isbn, year);
                
                if (borrowed) {
                    try {
                        foundBook.borrow();
                    } catch (BookNotAvailableException e) {
                        System.out.println(e.getMessage());
                    }
                }
                
                books.add(foundBook);

            }
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    //borrowed by id
    public static boolean borrowedBookById(int id) {
        
        try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(
        "UPDATE books SET borrowed = 1 Where id = ?"
    )){
        statement.setInt(1, id);
        int rowUpdated = statement.executeUpdate();
        return rowUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    public static boolean returnBookById(int id) {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE books SET borrowed = 0 WHERE id = ? AND borrowed = 1")) {

            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
        public static boolean removeBookById(int id) {
            try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM books WHERE id = ?")) {

                statement.setInt(1, id);

                int rowsDeleted = statement.executeUpdate();
                return rowsDeleted > 0;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

}
