/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.model.Enroll;

/**
 *
 * @author lolip
 */
public class Pagination {
    
    private DatabaseConnection dbConnection;
    
    public Pagination(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public int getTotalRecordsCourse(List<Course> result) {
        return result.size();
    }
    
    public List<Course> coursePaginateMoreData(List<Course> result, int offset, int limit) {
        List<Course> data = new ArrayList<>();
        for (int i = 0; i < Math.min(offset + limit, result.size()); i++) {
            data.add(result.get(i));
        }
        return data;
    }
     
    public List<Category> getIndexPaginationCategory(int offset, int limit) throws SQLException {
        List<Category> listCat = new ArrayList();
        String sql = "SELECT * FROM categories ORDER BY id ASC LIMIT ? OFFSET ? ";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");

            Category cat = new Category(id, name, description);
            listCat.add(cat);
        }
   
        return listCat;
    }
    
    public int getCountRecordsCategory() throws SQLException {
        int count = 0;
        
        String sql = "SELECT COUNT(*) AS count FROM categories ";
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        if(resultSet.next())
            count =   resultSet.getInt("count");
        
        return count;
    }
    
     public List<Course> getIndexPaginationCourseAll(int offset, int limit) throws SQLException {
        List<Course> listCourse = new ArrayList();
        
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "ORDER BY cs.id ASC "
                + "LIMIT ? OFFSET ? ";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, limit);
        statement.setInt(2, offset);
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
    
    public List<Course> getIndexPaginationCourse(int harcoded_tutor_id, int offset, int limit) throws SQLException {
        List<Course> listCourse = new ArrayList();
        
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "WHERE cs.tutor_id=? "
                + "ORDER BY cs.id ASC "
                + "LIMIT ? OFFSET ? ";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, harcoded_tutor_id);
        statement.setInt(2, limit);
        statement.setInt(3, offset);
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
    
    public int getCountRecordsCourseAll() throws SQLException {
        int count = 0;
     
        String sql = "SELECT COUNT(*) AS count FROM courses ";
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        if(resultSet.next())
            count = resultSet.getInt("count");
 
        return count;
    }
    
    public int getCountRecordsCourse(int harcoded_tutor_id) throws SQLException {
        int count = 0;
     
        String sql = "SELECT COUNT(*) AS count FROM courses WHERE tutor_id=? ";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1,harcoded_tutor_id);
        ResultSet resultSet = statement.executeQuery();
        
        if(resultSet.next())
            count = resultSet.getInt("count");
 
        return count;
    }
    
    public List<Chapter> getIndexPaginationChapter(int id_cor, int offset, int limit) throws SQLException {
        List<Chapter> listChapter = new ArrayList();

        String sql = "SELECT * "
                + "FROM chapters chp "
                + "WHERE chp.course_id=? "
                + "ORDER BY chp.id ASC "
                + "LIMIT ? OFFSET ? ";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id_cor);
        statement.setInt(2, limit);
        statement.setInt(3, offset);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int course_id = resultSet.getInt("course_id");
            String title = resultSet.getString("title");
            String name = resultSet.getString("name");

            String videopath = resultSet.getString("videopath");
            String attachmentpath = resultSet.getString("attachmentpath");

            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            String level = resultSet.getString("level");

            Chapter chapter = new Chapter(id, course_id, title, name, videopath, attachmentpath, description, status, level);
            listChapter.add(chapter);
        }

        return listChapter;
    }
     
    public int getCountRecordsChapter(int id_cor) throws SQLException {
        int count = 0;
     
        String sql = "SELECT COUNT(*) AS count FROM chapters WHERE course_id=? ";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1,id_cor);
        ResultSet resultSet = statement.executeQuery();
        
        if(resultSet.next())
            count = resultSet.getInt("count");
 
        return count;
    }
    
    public List<Course> getIndexCountByStudent(int harcoded_tutor_id, int offset, int limit) throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT c.id,c.name,c.durations,c.difficulties,count(e.student_id) AS total_student "
                + "FROM courses c "
                + "LEFT JOIN enrolls e "
                + "ON c.id = e.course_id "
                + "WHERE c.tutor_id=? "
                + "GROUP BY  c.id,c.name,c.durations,c.difficulties "
                + "LIMIT ? OFFSET ? ";
       
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, harcoded_tutor_id);
        statement.setInt(2, limit);
        statement.setInt(3, offset);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int duration = resultSet.getInt("durations");
            String difficulties = resultSet.getString("difficulties");
            int total_student = resultSet.getInt("total_student");

            Course course = new Course(id, name, duration, difficulties, total_student);
            listCourse.add(course);
        }

        return listCourse;
    }
    
    public List<Enroll> getIndexStudentByCourse(int course_id, int offset, int limit) throws SQLException  {
        List<Enroll> listEnroll = new ArrayList();
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "SELECT  en.id,en.student_id,en.course_id,en.date_enroll,usr.name as username,cor.name as coursename "
                + "FROM enrolls en "
                + "INNER JOIN users usr "
                + "ON en.student_id = usr.id "
                + "INNER JOIN courses cor "
                + "ON en.course_id = cor.id "
                + "WHERE en.course_id  = ? "
                + "ORDER BY en.id ASC "
                + "LIMIT ? OFFSET ? ";


        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, course_id);
        statement.setInt(2, limit);
        statement.setInt(3, offset);
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
    
    public int getCountRecordsEnroll(int course_id) throws SQLException {
        int count = 0;
     
        String sql = "SELECT COUNT(*) AS count FROM enrolls WHERE course_id=? ";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1,course_id);
        ResultSet resultSet = statement.executeQuery();
        
        if(resultSet.next())
            count = resultSet.getInt("count");
 
        return count;
    }
    
    
}
