package org.skillspark.onlinelearningplatform.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.skillspark.onlinelearningplatform.controller.LoginServlet;

import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.repository.CourseRepository;

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

    public List<Course> listAll(int harcoded_tutor_id) throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "WHERE cs.tutor_id=? "
                + "ORDER BY cs.id ASC";

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
    
    public List<Course> listAllByCountStudent(int harcoded_tutor_id) throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT c.id,c.name,c.durations,c.difficulties,count(e.student_id) AS total_student "
                + "FROM courses c "
                + "LEFT JOIN enrolls e "
                + "ON c.id = e.course_id "
                + "WHERE c.tutor_id=? "
                + "GROUP BY  c.id,c.name,c.durations,c.difficulties ";
       
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, harcoded_tutor_id);
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

    public void store(String course_name, int course_category, int course_duration, String course_difficulties, int course_status, String course_description, int harcoded_tutor_id) throws SQLException {
        String sql = "INSERT INTO courses (category_id, tutor_id, name, durations, description, status, difficulties, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        Date currentDate = new java.sql.Date(System.currentTimeMillis());
        
        statement.setInt(1, course_category);
        statement.setInt(2, harcoded_tutor_id);
        statement.setString(3, course_name);
        statement.setInt(4, course_duration);
        statement.setString(5, course_description);
        statement.setInt(6, course_status);
        statement.setString(7, course_difficulties);
        statement.setDate(8, currentDate);
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
    
     public Course getInfo(int id) throws SQLException {
        String sql = "SELECT cs.id,cs.category_id,cs.tutor_id,cs.durations,cs.status,cs.name,cs.description,cs.difficulties,cat.name as category_name,usr.name as username "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "INNER JOIN users usr "
                + "ON cs.tutor_id = usr.id "
                + "WHERE cs.id=?";
        
        int course_id = 0, category_id = 0, tutor_id = 0, duration = 0, status = 0;
        String name = "", description = "", difficulties = "", category_name="", username="test";

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
            category_name = resultSet.getString("category_name");
            username = resultSet.getString("username");
            
        }
          return new Course(course_id, category_id, tutor_id, name, duration, description, status, difficulties, category_name, username);
    }
     
    public List<Course> listAll() throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "ORDER BY cs.id ASC";

        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

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
    
    public List<Course> searchList(String search)  throws SQLException  {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "WHERE cs.name LIKE ? "
                + "ORDER BY cs.id ASC";
        String searchPattern = "%"+ search +"%";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
      
        statement.setString(1, searchPattern);
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
    
    public List<Course> searchListTutor(int search_tutor_id,String search) throws SQLException {
        List<Course> listCourse = new ArrayList();
        String sql = "SELECT cs.id ,cs.category_id,cs.tutor_id,cs.name,cs.durations,cs.description,cs.status,cs.difficulties,cat.name as category_name "
                + "FROM courses cs "
                + "INNER JOIN categories cat "
                + "ON cs.category_id = cat.id "
                + "WHERE cs.name LIKE ? "
                + "AND cs.tutor_id = ? "
                + "ORDER BY cs.id ASC";
        String searchPattern = "%"+ search +"%";

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
     
        statement.setString(1, searchPattern);
        statement.setInt(2, search_tutor_id);
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



    /*amirul method*/
    public Map<String, List<Course>> listAllByCategory3() throws SQLException {
        Map<String, List<Course>> coursesByCategory = new HashMap<>();
        String sql = CourseRepository.LIST_3_COURSE_PER_CATEGORY;

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


    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = CourseRepository.LIST_ALL_COURSE;

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int categoryId = resultSet.getInt("category_id");
            int tutorId = resultSet.getInt("tutor_id");
            String name = resultSet.getString("name");
            int duration = resultSet.getInt("durations");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            String difficulties = resultSet.getString("difficulties");

            Course course = new Course(id, categoryId, tutorId, name, duration, description, status, difficulties);
            courses.add(course);

        }
        return courses;
    }

    public List<Course> getCoursesByCategory(String category) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = CourseRepository.LIST_ALL_COURSE_BY_CATEGORY;

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        statement.setString(1, category);
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
            courses.add(course);
        }

        return courses;
    }

    public Map<String, List<String>> listDistinctCourseNames() throws SQLException {
        Map<String, List<String>> courseNamesMap = new HashMap<>();
        String sql = CourseRepository.LIST_UNIQUE_COURSE_NAME;

        PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String categoryName = resultSet.getString("category_name");
            if (!courseNamesMap.containsKey(categoryName)) {
                courseNamesMap.put(categoryName, new ArrayList<>());
            }
            courseNamesMap.get(categoryName).add(categoryName);
        }

        return courseNamesMap;
    }

    public Course getCourseById(Integer courseId) throws SQLException {
        String sql = CourseRepository.ALL_COURSE_BY_SELECTED_ID;
        try (PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int categoryId = resultSet.getInt("category_id");
                    int tutorId = resultSet.getInt("tutor_id");
                    String name = resultSet.getString("name");
                    int duration = resultSet.getInt("durations");
                    String description = resultSet.getString("description");
                    int status = resultSet.getInt("status");
                    String difficulties = resultSet.getString("difficulties");

                    return new Course(id, categoryId, tutorId, name, duration, description, status, difficulties);
                } else {
                    return null;
                }
            }
        }
    }
}
