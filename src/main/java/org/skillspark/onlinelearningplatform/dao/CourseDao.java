package org.skillspark.onlinelearningplatform.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Course;

public class CourseDao {

    private DatabaseConnection dbConnection;

    public CourseDao(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Optional<Course> find(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id=?";
        int course_id = 0, category_id = 0, tutor_id = 0, duration = 0, status = 0;
        String name = "", description = "", difficulties = "";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            course_id = resultSet.getInt("id");
            category_id = resultSet.getInt("category_id");
            tutor_id = resultSet.getInt("tutor_id");
            duration = resultSet.getInt("durations");
            status = resultSet.getInt("status");
            name = resultSet.getString("name");
            description = resultSet.getString("description");
            difficulties = resultSet.getString("difficulties");
        }

        return Optional.of(new Course(course_id, category_id, tutor_id, name, duration, description, status, difficulties));
    }

    public List<Course> listAll() throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "WHERE cs.tutor_id=? "
                + "ORDER BY cs.id ASC";

        //hard coded tutor_id
        int harcoded_tutor_id = 1;

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, harcoded_tutor_id);
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

    public void store(String course_name, int course_category, int course_duration, String course_difficulties, int course_status, String course_description) throws SQLException {
        String sql = "INSERT INTO courses (category_id,tutor_id,name,durations,description,status,difficulties) VALUES (?,?,?,?,?,?,?)";

        //hard coded tutor_id
        int harcoded_tutor_id = 1;

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, course_category);
        statement.setInt(2, harcoded_tutor_id);
        statement.setString(3, course_name);
        statement.setInt(4, course_duration);
        statement.setString(5, course_description);
        statement.setInt(6, course_status);
        statement.setString(7, course_difficulties);
        statement.executeUpdate();
    }

    public boolean update(Course course) throws SQLException {
        boolean rowupdate = false;
        String sql = "UPDATE courses SET category_id=? ,tutor_id=? ,name=? ,durations=? ,description=? ,status=? ,difficulties=? WHERE id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, course.getCategory_id());
        statement.setInt(2, course.getTutor_id());
        statement.setString(3, course.getName());
        statement.setInt(4, course.getDuration());
        statement.setString(5, course.getDescription());
        statement.setInt(6, course.getStatus());
        statement.setString(7, course.getDifficulties());
        statement.setInt(8, course.getId());
        rowupdate = statement.executeUpdate() > 0;

        return rowupdate;
    }

    public boolean delete(Course course) throws SQLException {
        boolean rowdelete = false;
        String sql = "DELETE FROM courses WHERE id=?";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, course.getId());
        rowdelete = statement.executeUpdate() > 0;

        return rowdelete;
    }

    public Map<String, List<Course>> listAllByCategory() throws SQLException {
        Map<String, List<Course>> coursesByCategory = new HashMap<>();
        String sql = "SELECT cs.id, cs.category_id, cs.tutor_id, cs.name, cs.durations, cs.description, cs.status, cs.difficulties, cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat ON cs.category_id = cat.id "
                + "WHERE cs.id IN ( "
                + "    SELECT c2.id "
                + "    FROM courses c2 "
                + "    WHERE c2.category_id = cs.category_id "
                + "    ORDER BY c2.id ASC "
                + "    LIMIT 3 "
                + ") "
                + "ORDER BY cs.category_id ASC, cs.id ASC";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
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

            if (!coursesByCategory.containsKey(category_name)) {
                coursesByCategory.put(category_name, new ArrayList<>());
            }
            coursesByCategory.get(category_name).add(course);
        }

        return coursesByCategory;
    }


}
