import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
