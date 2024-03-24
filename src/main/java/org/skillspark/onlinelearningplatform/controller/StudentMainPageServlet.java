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
import org.skillspark.onlinelearningplatform.dao.ChapterDao;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.EnrollDao;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;

/**
 *
 * @author lolip
 */
@WebServlet(name = "StudentMainPageServlet", value = "/StudentMainPageServlet")
public class StudentMainPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        try {
            switch (route) {
                case "view":
                    viewCouresContent(request, response);
                    break;
                default:
                    showListIndexT(request, response);
                    break;
            }
        } catch (SQLException ex) {

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
     private void showListIndexT(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/CatalogPage.jsp");

        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
            
        List<Course> listCourse = courseDao.listAll();
        request.setAttribute("listCourse",listCourse);
        
        dispatcher.forward(request,response);
    }

    private void viewCouresContent(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
        ChapterDao chapterDao = new ChapterDao(dbConnection);
        EnrollDao enrollDao = new EnrollDao(dbConnection);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/content/viewCourses/index.jsp");
    
        Course course = courseDao.getInfo(id);
        request.setAttribute("course",course);
        
        List<Chapter> listChapter = chapterDao.listAll(id);
        request.setAttribute("listChapter",listChapter);
        
        boolean isEnroll = enrollDao.checkEnrollStudent(student_id,id);
        request.setAttribute("isEnroll",isEnroll);
     
        dispatcher.forward(request,response);
    }

}