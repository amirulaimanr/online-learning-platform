/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.skillspark.onlinelearningplatform.model.Chapter;

/**
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
        String title = "", name = "", video_path = "", attachment_path = "", description = "", level = "";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            chapter_id = resultSet.getInt("id");
            course_id = resultSet.getInt("course_id");
            title = resultSet.getString("title");
            name = resultSet.getString("name");
            
            video_path = resultSet.getString("videopath");
            attachment_path = resultSet.getString("attachmentpath");

            description = resultSet.getString("description");
            status = resultSet.getInt("status");
            level = resultSet.getString("level");
        }

        return Optional.of(new Chapter(chapter_id, course_id, title, name, video_path, attachment_path, description, status, level));
    }

    public List<Chapter> listAll(int id_cor) throws SQLException {
        List<Chapter> listChapter = new ArrayList();

        String sql = "SELECT * "
                + "FROM chapters chp "
                + "WHERE chp.course_id=? "
                + "ORDER BY chp.id ASC";


        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id_cor);
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

    public int store(int course_id, String title, String name, String videopath, String attachmentpath, String description, int status, String level) throws SQLException {
        String sql = "INSERT INTO chapters (course_id,title,name,videopath,attachmentpath,description,status,level,created_at) VALUES (?,?,?,?,?,?,?,?,?)";
        Date currentDate = new Date(System.currentTimeMillis());

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, course_id);
        statement.setString(2, title);
        statement.setString(3, name);
        statement.setString(4, videopath);
        statement.setString(5, attachmentpath);
        statement.setString(6, description);
        statement.setInt(7, status);
        statement.setString(8, level);
        statement.setDate(9, currentDate);
        statement.executeUpdate();

        ResultSet result = statement.getGeneratedKeys();

        if (result.next() && result != null)
            return result.getInt(1);
        else
            return 0;
    }

    public boolean update(Chapter chapter) throws SQLException {
        boolean rowupdate = false;
        String sql = "UPDATE chapters SET title=? ,name=? ,description=? ,status=? ,level=? WHERE id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, chapter.getTitle());
        statement.setString(2, chapter.getName());
        statement.setString(3, chapter.getDescription());
        statement.setInt(4, chapter.getStatus());
        statement.setString(5, chapter.getLevel());
        statement.setInt(6, chapter.getId());
        rowupdate = statement.executeUpdate() > 0;

        return rowupdate;
    }

    public boolean updateVideoPath(int chapter_id, String videopath) throws SQLException {
        boolean rowupdate = false;
        String sql = "UPDATE chapters SET videopath=? WHERE id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, videopath);
        statement.setInt(2, chapter_id);
        rowupdate = statement.executeUpdate() > 0;

        return rowupdate;
    }

    public boolean delete(Chapter chapter) throws SQLException {
        boolean rowdelete = false;
        String sql = "DELETE FROM chapters WHERE id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, chapter.getId());
        rowdelete = statement.executeUpdate() > 0;

        return rowdelete;
    }

    /*amirul method*/
    public List<Chapter> getChaptersByCourseId(int courseId) throws SQLException {
        List<Chapter> chapters = new ArrayList<>();
        String sql = "SELECT * FROM chapters WHERE course_id = ? ORDER BY id";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, courseId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Chapter chapter = new Chapter();
            chapter.setId(resultSet.getInt("id"));
            chapter.setCourse_id(resultSet.getInt("course_id"));
            chapter.setTitle(resultSet.getString("title"));
            chapter.setName(resultSet.getString("name"));

            chapter.setVideopath(resultSet.getString("videopath"));
            chapter.setAttachmentpath(resultSet.getString("attachmentpath"));

            chapter.setDescription(resultSet.getString("description"));
            chapter.setStatus(resultSet.getInt("status"));
            chapter.setLevel(resultSet.getString("level"));

            chapters.add(chapter);
        }
        return chapters;
    }
}
