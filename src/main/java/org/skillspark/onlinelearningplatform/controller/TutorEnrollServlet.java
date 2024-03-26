/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.EnrollDao;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.model.Enroll;

/**
 *
 * @author lolip
 */
@WebServlet(name = "TutorEnrollServlet", value = "/TutorEnrollServlet")
public class TutorEnrollServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        try {
            switch (route) {
                case "view":
                    viewStudent(request, response);
                    break;
                case "delete":
                    deleteEnroll(request, response);
                    break;
                default:
                    showListIndexT(request, response);
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("Error sql: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void showListIndexT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/enroll/index.jsp");
        try {

            int id = Integer.parseInt(request.getParameter("tutor_id"));

            DatabaseConnection dbConnection = new DatabaseConnection();
            CourseDao courseDao = new CourseDao(dbConnection);

            List<Course> listCourse = courseDao.listAllByCountStudent(id);
            request.setAttribute("listCourse", listCourse);
        } catch (SQLException ex) {
            System.err.println("Error sql: " + ex.getMessage());
        }
        dispatcher.forward(request, response);
    }

    private void viewStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/enroll/view_student.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        String course_name = request.getParameter("course_name");

        DatabaseConnection dbConnection = new DatabaseConnection();
        EnrollDao enrollDao = new EnrollDao(dbConnection);

        List<Enroll> listEnroll = enrollDao.listStudentByCourse(id);
        request.setAttribute("listStudent", listEnroll);
        request.setAttribute("course_name", course_name);
          
        dispatcher.forward(request, response);
    }

    private void deleteEnroll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int course_id = Integer.parseInt(request.getParameter("id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        String course_name = request.getParameter("course_name");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        EnrollDao enrollDao = new EnrollDao(dbConnection);
        enrollDao.delete(student_id, course_id);

        request.getSession().setAttribute("success", "Enrolled student succesffully deleted");
        response.sendRedirect("/TutorEnrollServlet?route=view&id="+course_id+"&course_name="+course_name);
    }

}
