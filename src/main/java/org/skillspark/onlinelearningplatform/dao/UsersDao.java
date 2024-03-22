package org.skillspark.onlinelearningplatform.dao;

import org.skillspark.onlinelearningplatform.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skillspark.onlinelearningplatform.model.Users;

public class UsersDao {
    private DatabaseConnection dbConnection;

    public UsersDao(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addUser(String email, String password, int roleId) throws SQLException {
        String sql = UserRepository.INSERT_USERS_BY_EMAIL_PASSWORD_ROLE;
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        statement.setInt(3, roleId);
        statement.executeUpdate();
    }

    public boolean exists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next() && resultSet.getInt(1) > 0;
    }
    
            
    public Users getUserInfo(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        int id = 0, role_id = 0;
        String name = "", email_user = "";

        while (resultSet.next()) {
            id = resultSet.getInt("id");
            role_id = resultSet.getInt("role_id");
            name = resultSet.getString("name");
            email_user = resultSet.getString("email");
        }

        return new Users(id, role_id, name, email_user);
    }
}
