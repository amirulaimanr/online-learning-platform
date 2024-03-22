/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;

/**
 *
 * @author lolip
 */
public class ChapterDao {
    private DatabaseConnection dbConnection;

    public ChapterDao(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Optional<Chapter> find(int id) throws SQLException {
        String sql = "SELECT * FROM chapters WHERE id=?";
        int chapter_id = 0, course_id = 0, status = 0;
        String title = "", name = "", video_path = "", attachment_path="", description = "", level = "";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            chapter_id = resultSet.getInt("id");
            course_id = resultSet.getInt("course_id");
            title = resultSet.getString("title");
            name = resultSet.getString("name");
            video_path = resultSet.getString("video_path");
            attachment_path = resultSet.getString("attachment_path");
            description = resultSet.getString("description");
            status = resultSet.getInt("status");
            level = resultSet.getString("level");
        }
        
        return Optional.of(new Chapter(chapter_id, course_id, title, name, video_path, attachment_path, description, status, level));
    }
              
    public List<Chapter> listAll(int id_cor) throws SQLException {
        List<Chapter> listChapter = new ArrayList();
    
        String sql = "SELECT chp.id,chp.course_id,chp.title,chp.name,chp.video_path,chp.attachment_path,chp.description,chp.status,chp.level,cs.name as course_name "
                + "FROM chapters chp "
                + "INNER JOIN courses cs "
                + "ON chp.course_id = cs.id "
                + "WHERE chp.course_id=? "
                + "ORDER BY chp.id ASC";

        //hard coded tutor_id

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id_cor);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int course_id = resultSet.getInt("course_id");
            String title = resultSet.getString("title");
            String name = resultSet.getString("name");
            String video_path = resultSet.getString("video_path");
            String attachment_path = resultSet.getString("attachment_path");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            String level = resultSet.getString("level");
            String course_name = resultSet.getString("course_name");
            

            Chapter chapter = new Chapter(id, course_id, title, name, video_path, attachment_path, description, status, level, course_name);
            listChapter.add(chapter);
        }

        return listChapter;
    }
    
    public int store(int course_id, String title, String name, String video_path, String attachment_path, String description, int status, String level) throws SQLException {
        String sql = "INSERT INTO chapters (course_id,title,name,video_path,attachment_path,description,status,level) VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, course_id);
        statement.setString(2, title);
        statement.setString(3, name);
        statement.setString(4, video_path);
        statement.setString(5, attachment_path);
        statement.setString(6, description);
        statement.setInt(7, status);
        statement.setString(8, level);
        statement.executeUpdate();
        
        ResultSet result = statement.getGeneratedKeys();
        
        if(result.next() && result != null)
             return result.getInt(1);
        else
            return 0;
    }
    
     public boolean updateVideoPath(int chapter_id,String video_path) throws SQLException {
        boolean rowupdate = false;
        String sql = "UPDATE chapters SET video_path=? WHERE id=?";
         
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, video_path);
        statement.setInt(2, chapter_id);
        rowupdate = statement.executeUpdate() > 0;

        return rowupdate;
    }

}
