/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author lolip
 */
public class EnrollDao {

    private DatabaseConnection dbConnection;

    public EnrollDao(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public EnrollDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void store(int student_id, int course_id) throws SQLException {
        String sql = "INSERT INTO enrolls (student_id, course_id,date_enroll) VALUES (?, ?, ?)";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);

        Date currentDate = new Date(System.currentTimeMillis());

        statement.setInt(1, student_id);
        statement.setInt(2, course_id);
        statement.setDate(3, currentDate);
        statement.executeUpdate();
    }

    public boolean checkEnrollStudent(int student_id, int course_id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM enrolls WHERE student_id=? AND course_id=?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, student_id);
        statement.setInt(2, course_id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next() && resultSet.getInt(1) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
