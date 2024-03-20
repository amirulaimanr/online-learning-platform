package org.skillspark.onlinelearningplatform.service;

import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    private DatabaseConnection dbConnection;
    public AuthService() throws SQLException {
        this.dbConnection = new DatabaseConnection();
    }

    public boolean authenticate(String email, String password) {
        String sql = UserRepository.COUNT_USERS_BY_EMAIL_AND_PASSWORD;
        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
