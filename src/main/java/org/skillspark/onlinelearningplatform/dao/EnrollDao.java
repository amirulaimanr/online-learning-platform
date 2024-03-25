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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.model.Enroll;

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
    
    public List<Course> listAll(int student_id) throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "WHERE cs.id IN (SELECT course_id FROM enrolls WHERE student_id = ?)"
                + "ORDER BY cs.id ASC";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, student_id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int category_id = resultSet.getInt("category_id");
            int tutor_id = resultSet.getInt("tutor_id");
            String name = resultSet.getString("name");
            int duration = resultSet.getInt("durations");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            String difficulties = resultSet.getString("difficulties");
            String category_name = resultSet.getString("category_name");

            Course course = new Course(id, category_id, tutor_id, name, duration, description, status, difficulties, category_name);
            listCourse.add(course);
        }
        return listCourse;
    }
    
    public boolean delete(int student_id, int course_id) throws SQLException {
        boolean rowdelete = false;
        String sql = "DELETE FROM enrolls WHERE student_id=? AND course_id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, student_id);
        statement.setInt(2, course_id);
        rowdelete = statement.executeUpdate() > 0;

        return rowdelete;
    }
    
    public List<Enroll> listStudentByCourse(int course_id) throws SQLException  {
        List<Enroll> listEnroll = new ArrayList();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT  en.id,en.student_id,en.course_id,en.date_enroll,usr.name as username,cor.name as coursename "
                + "FROM enrolls en "
                + "INNER JOIN users usr "
                + "ON en.student_id = usr.id "
                + "INNER JOIN courses cor "
                + "ON en.course_id = cor.id "
                + "WHERE en.course_id  = ?"
                + "ORDER BY en.id ASC";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, course_id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int student_id = resultSet.getInt("student_id");
            int course_id_result = resultSet.getInt("course_id");
            String username = resultSet.getString("username");
            String coursename = resultSet.getString("coursename");
            Date date = resultSet.getDate("date_enroll");

            String date_enroll = sdt.format(date);

            Enroll enroll = new Enroll(id, course_id_result, student_id, date_enroll, coursename, username);
            listEnroll.add(enroll);
        }
        
        return listEnroll;
    }
    
   
}
