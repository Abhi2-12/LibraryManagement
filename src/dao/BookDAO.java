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

            return new Book(bookId , title, author, isbn, year);
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
                
                Book foundBook = new Book(id, bookTitle, author, isbn, year);
                books.add(foundBook);

                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

}
