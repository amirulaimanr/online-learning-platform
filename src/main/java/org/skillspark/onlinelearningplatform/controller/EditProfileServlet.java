/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.UsersDao;
import org.skillspark.onlinelearningplatform.model.Users;

@WebServlet(name = "EditProfileServlet", value = "/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        try {
            if(route.equalsIgnoreCase("edit")){
                showEditForm(request, response);
            }else if(route.equalsIgnoreCase("update")){
                updateUser(request, response);
            }
        } catch (SQLException ex) {
            System.err.println("Error sql: " + ex.getMessage());
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
        if(route.equalsIgnoreCase("index")){
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                DatabaseConnection dbConnection = new DatabaseConnection();
                UsersDao userDao = new UsersDao(dbConnection);
                
                Users user = userDao.find(id);
                request.setAttribute("user", user);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/UpdateUser.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            String role_id = request.getParameter("role_id").trim();
            int id = Integer.parseInt(request.getParameter("id"));
            if (role_id.equalsIgnoreCase("1")) {
                response.sendRedirect("/TutorMainPageServlet?route=index&tutor_id="+id);
            }else{
                response.sendRedirect("/StudentMainPageServlet?route=index&id");
            }
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    DatabaseConnection dbConnection = new DatabaseConnection();
    UsersDao userDao = new UsersDao(dbConnection);

    Users user = userDao.find(id);
    request.setAttribute("user", user);

    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/UpdateUser.jsp");
    dispatcher.forward(request, response);
}


    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String user_name = request.getParameter("user_name");
        String user_mail = request.getParameter("user_mail");
        String user_password = request.getParameter("user_password");
        try{
            Users user = new Users(user_id, user_name, user_mail, user_password);
            DatabaseConnection dbConnection = new DatabaseConnection();
            UsersDao userDao = new UsersDao(dbConnection);

            userDao.update(user);
        }catch (SQLException ex){
            System.err.println("Error sql: " + ex.getMessage());
        }
        request.getSession().setAttribute("success", "User successfully updated");
        response.sendRedirect("/EditProfileServlet?route=index&id="+user_id);
    }
    
}
