/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


}
