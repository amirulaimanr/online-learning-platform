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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.UsersDao;
import org.skillspark.onlinelearningplatform.model.Course;

/**
 *
 * @author lolip
 */
@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String search = request.getParameter("search");
        try {
              DatabaseConnection dbConnection = new DatabaseConnection();
              UsersDao userDao = new UsersDao(dbConnection);
              CourseDao courseDao = new CourseDao(dbConnection);

              String role = userDao.checkUserRole(id);
              if (role.equals("Tutor")) {
                  RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/CatalogPage.jsp");

                  List<Course> listCourse = courseDao.searchListTutor(id,search);
                  request.setAttribute("listCourse", listCourse);

                  dispatcher.forward(request, response);
              } else if (role.equals("Student")) {
                  RequestDispatcher dispatcher = request.getRequestDispatcher("/student/CatalogPage.jsp");

                  List<Course> listCourse = courseDao.searchList(search);
                  request.setAttribute("listCourse", listCourse);

                  dispatcher.forward(request, response);
              }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
