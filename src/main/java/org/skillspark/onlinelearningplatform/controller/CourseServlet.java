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
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.skillspark.onlinelearningplatform.dao.CategoryDao;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Course;

/**
 *
 * @author lolip
 */
@WebServlet(name = "CoursesServlet", value = "/CoursesServlet")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String route = request.getParameter("route");
           try{
                switch (route){
                    case "create" :
                        showCreateForm(request,response);
                       break;
                    case "store":
                        storeCourse(request,response);
                        break;
                    case "edit":
                        showEditForm(request,response);
                        break;
                    case "update":
                        updateCourse(request,response);
                        break;
                    case "delete":
                        deleteCourse(request,response);
                        break;
                    default:
                        showListIndexT(request,response);
                        break;
                }
           }catch(SQLException ex){
               
           }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void showListIndexT(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/courses/index.jsp");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
            
        List<Course> listCourse = courseDao.listAll();
        request.setAttribute("listCourse",listCourse);
        
        dispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/courses/create.jsp");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        CategoryDao catDao = new CategoryDao(dbConnection);
            
        List<Category> listCat = catDao.listAll();
        request.setAttribute("listCategory",listCat);
        
        dispatcher.forward(request,response);
    }

    private void storeCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        String course_name = request.getParameter("course_name");
        int course_category = Integer.parseInt(request.getParameter("course_category"));
        int course_duration = Integer.parseInt(request.getParameter("course_duration"));
        String course_difficulties = request.getParameter("course_difficulties");
        int course_status = Integer.parseInt(request.getParameter("course_status"));
        String course_description = request.getParameter("course_description");

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            HttpSession session = request.getSession();

            CourseDao courseDao = new CourseDao(dbConnection);
            courseDao.store(course_name, course_category, course_duration, course_difficulties, course_status, course_description);
            request.getSession().setAttribute("success", "Category succesffully added");
            response.sendRedirect("/CourseServlet?route=index");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
        CategoryDao catDao = new CategoryDao(dbConnection);
        
        Optional<Course> course = courseDao.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/courses/edit.jsp");
        course.ifPresent(s->request.setAttribute("course",s));
        
        List<Category> listCat = catDao.listAll();
        request.setAttribute("listCategory",listCat);
        
        dispatcher.forward(request,response);
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String course_name = request.getParameter("course_name");
        int course_category = Integer.parseInt(request.getParameter("course_category"));
        int course_duration = Integer.parseInt(request.getParameter("course_duration"));
        String course_difficulties = request.getParameter("course_difficulties");
        int course_status = Integer.parseInt(request.getParameter("course_status"));
        String course_description = request.getParameter("course_description");
        
        // hard_coded tutor id
        int hard_coded_tutor_id = 1;
        
        Course course = new Course(course_id,course_category,hard_coded_tutor_id,course_name,course_duration,course_description,course_status,course_difficulties);
        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
        
        courseDao.update(course);
        request.getSession().setAttribute("success", "Course succesffully updated");
        response.sendRedirect("/CourseServlet?route=index");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException  {
        int id = Integer.parseInt(request.getParameter("id"));
        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
            
        Course course = new Course(id);
        
        courseDao.delete(course);
        request.getSession().setAttribute("success", "Course succesffully deleted");
        response.sendRedirect("/CourseServlet?route=index");
    }
}
