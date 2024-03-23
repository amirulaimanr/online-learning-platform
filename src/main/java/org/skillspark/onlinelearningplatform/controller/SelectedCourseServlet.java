package org.skillspark.onlinelearningplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.dao.CourseDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SelectedCourseServlet", value = "/SelectedCourseServlet")
public class SelectedCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseIdString = request.getParameter("course_id");
        int courseId = Integer.parseInt(courseIdString);

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            CourseDao courseDao = new CourseDao(dbConnection);
            Course course = courseDao.getCourseById(courseId);

            if (course != null) {
                request.setAttribute("course", course);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/SelectedCoursePage.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Course not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching course details");
        }
    }
}

