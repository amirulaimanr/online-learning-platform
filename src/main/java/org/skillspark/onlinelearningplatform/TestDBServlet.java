package org.skillspark.onlinelearningplatform;

import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();

            String query = "SELECT name, email FROM users";
            try (
                    ResultSet resultSet = dbConnection.executeQuery(query)) {

                out.println("<h2>Users:</h2>");
                out.println("<ul>");
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    out.println("<li>Name: " + name + ", Email: " + email + "</li>");
                }
                out.println("</ul>");
            } catch (SQLException e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                dbConnection.close();
            }
        } catch (SQLException e) {
            out.println("<p>Error establishing database connection: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
