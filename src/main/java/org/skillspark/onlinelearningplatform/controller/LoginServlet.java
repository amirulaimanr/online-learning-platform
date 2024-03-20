package org.skillspark.onlinelearningplatform.controller;

import org.skillspark.onlinelearningplatform.service.AuthService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
            session.setAttribute("email", email);
            response.sendRedirect("/pages/UserDashboardPage.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/pages/LoginPage.jsp").forward(request, response);
        }
    }

}