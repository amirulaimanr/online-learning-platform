package org.skillspark.onlinelearningplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FetchCategoryCourseServlet", value = "/FetchCategoryCourseServlet")
public class FetchCategoryCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("action");
        String category = request.getParameter("category");
        try {
            switch (route) {
                case "category_course":
                    getCategoryCourse(request, response);
                    break;
                case "course":
                    getCourseName(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {

        }
    }

    private void getCategoryCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            CourseDao courseDao = new CourseDao(dbConnection);
            Map<String, List<Course>> categories = courseDao.listAllByCategory3();

            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(categories);
            response.getWriter().write(jsonString);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching categories"); // Handle error gracefully with a proper status code and message
        }
    }

    private void getCourseName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            CourseDao courseDao = new CourseDao(dbConnection);
            Map<String, List<String>> data = courseDao.listDistinctCourseNames();

            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(data);
            response.getWriter().write(jsonString);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching categories"); // Handle error gracefully with a proper status code and message
        }
    }
}
