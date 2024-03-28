package org.skillspark.onlinelearningplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skillspark.onlinelearningplatform.dao.ChapterDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.dao.CourseDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
                ChapterDao chapterDao = new ChapterDao(dbConnection);
                List<Chapter> chapters = chapterDao.getChaptersByCourseId(courseId)
                        .stream()
                        .sorted((c1, c2) -> c1.getId() - c2.getId())
                        .collect(Collectors.toList());

                request.setAttribute("course", course);
                request.setAttribute("chapters", chapters);

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


