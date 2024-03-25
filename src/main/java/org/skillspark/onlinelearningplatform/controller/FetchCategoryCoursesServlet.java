package org.skillspark.onlinelearningplatform.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Course;

@WebServlet(name = "FetchCategoryCoursesServlet", value = "/FetchCategoryCoursesServlet")
public class FetchCategoryCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            CourseDao courseDao = new CourseDao(dbConnection);
            List<Course> courses = courseDao.getCoursesByCategory(category);

            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(courses);
            response.getWriter().write(jsonString);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching courses by category");
        }
    }

}