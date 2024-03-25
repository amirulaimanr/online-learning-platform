package org.skillspark.onlinelearningplatform.controller;

import org.skillspark.onlinelearningplatform.service.AuthService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.UsersDao;
import org.skillspark.onlinelearningplatform.model.Users;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthService authService = null;
        try {
            authService = new AuthService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (authService.authenticate(email, password)) {
            HttpSession session = request.getSession();
            Users user = null;
            DatabaseConnection dbConnection = null;
            
            // set session timmer
            int timeOut = 60 * 60 * 24;
            session.setMaxInactiveInterval(timeOut);
            
            // get info from users and store it in session
            try {
                dbConnection = new DatabaseConnection();
                UsersDao userDao = new UsersDao(dbConnection);
                user = userDao.getUserInfo(email, password);
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("user", user);

            if (user.getRole_id() == 1) {
                //tutor
                response.sendRedirect("/TutorMainPageServlet?route=index&tutor_id="+user.getId());
            } else {
                //students
                  response.sendRedirect("/StudentMainPageServlet?route=index");
            }
            
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/pages/LoginPage.jsp").forward(request, response);
        }
    }

}
