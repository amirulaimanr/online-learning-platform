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
            int timeOut = 60 * 60 * 24;
            session.setMaxInactiveInterval(timeOut);
            Users user = null;

            DatabaseConnection dbConnection = null;
            try {
                dbConnection = new DatabaseConnection();
                UsersDao userDao = new UsersDao(dbConnection);
                user = userDao.getUserInfo(email, password);
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("user", user);

            if (user.getId() == 1) {
                response.sendRedirect("/tutor/CatalogPage.jsp");
            } else {
                response.sendRedirect("/pages/UserDashboardPage.jsp");
            }
            
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/pages/LoginPage.jsp").forward(request, response);
        }
    }

}
