package dao;
import model.Member;
import model.Role;
import service.PasswordUtil;
import database.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class MemberDAO {
    //Registewr a member
    public boolean addMember(String name, String email, String passwordHash) {
       
        String sql = "INSERT INTO members (name,email,password_hash,role)"
                   + "VALUES (?, ?, ?, ?)";
    
        try (Connection connection =  DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement (sql)) {

                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, passwordHash);
                statement.setString(4, Role.MEMBER.name());
                

                return statement.executeUpdate() > 0;
              
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }        

    }

    //for login
    public Member login(String email, String password) {
        String sql = "SELECT id, name, email, password_hash, role "
        + "FROM members where email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            
            statement.setString(1, email);

            try(ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    
                    String storedHash = result.getString("password_hash");

                    if (PasswordUtil.verifyPassword(password, storedHash)) {
                        
                        return new Member(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("email"),
                            storedHash,
                            Role.valueOf(result.getString("role"))
                        );
                    }
                }
            }


            
        } catch (Exception e) {
            System.out.println("Login Failed : " + e.getMessage());
        }

        return null;
    }

}
