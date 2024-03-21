package org.skillspark.onlinelearningplatform.controller;

import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.UsersDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            UsersDao usersDao = new UsersDao(dbConnection);

            if (!usersDao.exists(email)) {
                usersDao.addUser(email, password, roleId);
                response.sendRedirect("/pages/LoginPage.jsp");
            } else {
                request.setAttribute("errorMessage", "Email already exists");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
