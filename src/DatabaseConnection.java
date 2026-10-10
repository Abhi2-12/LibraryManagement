
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "?";

        try {
            Connection connection = DriverManager.getConnection(
                url, username, password
            );

            System.out.println("MySQL connection successful!");

            Book book = new Book(
                0,
                "The Pragmatic Programmer",
                "Andrew Hunt",
                "9780135957059",
                2019
            );

            addBook(book);

            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            System.out.println(e.getMessage());
        }
    }

    public static void addBook(Book book) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO books (title, author, year, isbn) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getYear());
            statement.setString(4, book.getIsbn());

            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                System.out.println("Book added successfully");
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}