package org.skillspark.onlinelearningplatform.dao;

import org.skillspark.onlinelearningplatform.config.DatabaseConfig;
import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() throws SQLException {
        try {
            Class.forName(DatabaseConfig.getDbDriver());
            connection = DriverManager.getConnection(DatabaseConfig.getDbUrl(), DatabaseConfig.getDbUsername(), DatabaseConfig.getDbPassword());
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Failed to load JDBC driver", ex);
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    public void executeUpdate(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}


