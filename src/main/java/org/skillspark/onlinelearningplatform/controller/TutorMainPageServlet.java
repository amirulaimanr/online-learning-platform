/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.controller;

import java.io.IOException;
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
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;
import org.skillspark.onlinelearningplatform.util.Pagination;

/**
 *
 * @author lolip
 */
@WebServlet(name = "TutorMainPageServlet", value = "/TutorMainPageServlet")
public class TutorMainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
            System.err.println("Error sql: " + ex.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }

    private void showListIndexT(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/CatalogPage.jsp");
        int id = Integer.parseInt(request.getParameter("tutor_id"));

        DatabaseConnection dbConnection = new DatabaseConnection();
        Pagination paginate = new Pagination(dbConnection);
            
        int page = 1; 
        int recordsPerPage = 6; 

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        List<Course> paginateCor = paginate.getIndexPaginationCourse(id,0, page  * recordsPerPage);
        int totalRecords = paginate.getCountRecordsCourse(id);
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
   
        request.setAttribute("listCourse",paginateCor);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        
        dispatcher.forward(request,response);
    }

    private void viewCouresContent(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        CourseDao courseDao = new CourseDao(dbConnection);
        ChapterDao chapterDao = new ChapterDao(dbConnection);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/viewCourses/index.jsp");
    
        Course course = courseDao.getInfo(id);
        request.setAttribute("course",course);
        
        List<Chapter> listChapter = chapterDao.listAll(id);
        request.setAttribute("listChapter",listChapter);
     
        dispatcher.forward(request,response);
    }
}
