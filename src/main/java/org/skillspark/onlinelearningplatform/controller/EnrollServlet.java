/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.skillspark.onlinelearningplatform.dao.ChapterDao;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.EnrollDao;
import org.skillspark.onlinelearningplatform.dao.UsersDao;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;

/**
 *
 * @author lolip
 */
@WebServlet(name = "EnrollServlet", value = "/EnrollServlet")
public class EnrollServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        try {
            switch (route) {
                case "store":
                    storeEnroll(request, response);
                    break;
                case "view":
                    viewCouresContent(request, response);
                    break;
                case "delete":
                    deleteEnroll(request, response);
                    break;
                default:
                    viewEnrollCourse(request, response);
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("Error sql: " + ex.getMessage());
        };
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void storeEnroll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            HttpSession session = request.getSession();

            EnrollDao enrollDao = new EnrollDao(dbConnection);
            UsersDao userDao = new UsersDao(dbConnection);

            String role = userDao.checkUserRole(student_id);

            if (role.equals("Student")) {
                boolean isEnroll = enrollDao.checkEnrollStudent(student_id, course_id);

                if (isEnroll == true) {
                    response.sendRedirect("/StudentMainPageServlet?route=index");
                } else {
                    enrollDao.store(student_id, course_id);

                    request.getSession().setAttribute("success", "Course successfully enrolled");
                    response.sendRedirect("/EnrollServlet?route=index&student_id=" + student_id);
                }
            } else {
                response.sendRedirect("/TutorMainPageServlet?route=index&tutor_id=" + student_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

    }

    private void viewEnrollCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/content/enroll/index.jsp");

        DatabaseConnection dbConnection = new DatabaseConnection();
        EnrollDao enrollDao = new EnrollDao(dbConnection);

        List<Course> listCourse = enrollDao.listAll(student_id);
        request.setAttribute("listCourse", listCourse);

        dispatcher.forward(request, response);
    }

    private void viewCouresContent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));

        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
        ChapterDao chapterDao = new ChapterDao(dbConnection);
        EnrollDao enrollDao = new EnrollDao(dbConnection);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/content/enroll/view.jsp");

        Course course = courseDao.getInfo(id);
        request.setAttribute("course", course);

        List<Chapter> listChapter = chapterDao.listAll(id);
        request.setAttribute("listChapter", listChapter);

        boolean isEnroll = enrollDao.checkEnrollStudent(student_id, id);
        request.setAttribute("isEnroll", isEnroll);

        dispatcher.forward(request, response);
    }

    private void deleteEnroll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int course_id = Integer.parseInt(request.getParameter("id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));

        DatabaseConnection dbConnection = new DatabaseConnection();
        EnrollDao enrollDao = new EnrollDao(dbConnection);

        try {
            enrollDao.delete(student_id, course_id);
            request.getSession().setAttribute("success", "Enrolled course successfully deleted");
            response.sendRedirect("/EnrollServlet?route=index&student_id=" + student_id);
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("failed", "Enrolled course failed deleted");
            response.sendRedirect("/EnrollServlet?route=index&student_id=" + student_id);
        }
    }
}
