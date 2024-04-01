package org.skillspark.onlinelearningplatform.dao;

import java.sql.Date;
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

    public void addUser(String email, String password, int roleId, String name) throws SQLException {
        String sql = UserRepository.INSERT_USERS_BY_EMAIL_PASSWORD_ROLE;
        Date currentDate = new Date(System.currentTimeMillis());
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        statement.setInt(3, roleId);
        statement.setString(4, name);
        statement.setDate(5, currentDate);
        statement.setDate(6, currentDate);    
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

     public String checkUserRole(int user_id) throws SQLException {
        String sql = "SELECT rol.name as role_name "
                + "FROM users usr "
                + "JOIN roles as rol "
                + "ON usr.role_id = rol.id "
                + "WHERE usr.id=? ";
            
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, user_id);
        ResultSet resultSet = statement.executeQuery();
        
        String str = "";
        
        if (resultSet.next()) {
            str = resultSet.getString("role_name");
        } 
        
        return str;
    }
    public Users find(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        int id_user = 0, role_id = 0;
        String name = "", email_user = "", password = "";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            role_id = resultSet.getInt("role_id");
            id_user = resultSet.getInt("id");
            name = resultSet.getString("name");
            email_user = resultSet.getString("email");
            password = resultSet.getString("password");
        }

        return new Users(id_user, name, email_user, password, role_id);
    }
    public boolean update(Users user) throws SQLException {
        boolean rowupdate = false;
        String sql = "UPDATE users SET name=?,email=?,password=? WHERE id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getId());
        rowupdate = statement.executeUpdate() > 0;

        return rowupdate;
    }
}
