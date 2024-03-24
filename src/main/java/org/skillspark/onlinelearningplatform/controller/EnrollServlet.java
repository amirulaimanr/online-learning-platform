/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.skillspark.onlinelearningplatform.dao.CategoryDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.EnrollDao;

/**
 *
 * @author lolip
 */
@WebServlet(name = "EnrollServlet", value = "/EnrollServlet")
public class EnrollServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String route = request.getParameter("route");
        try {
            switch (route) {
                default:
                    storeEnroll(request, response);
                    break;
            }
        } catch (SQLException ex) {

        };
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }

    private void storeEnroll(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        
         try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            HttpSession session = request.getSession();

            EnrollDao enrollDao = new EnrollDao(dbConnection);
            enrollDao.store(student_id, course_id);
            request.getSession().setAttribute("success", "Course successfully enrolled");
            response.sendRedirect("/StudentMainPageServlet?route=index");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
        
    }



}
