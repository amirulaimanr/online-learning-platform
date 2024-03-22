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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.skillspark.onlinelearningplatform.dao.CategoryDao;
import org.skillspark.onlinelearningplatform.dao.ChapterDao;
import org.skillspark.onlinelearningplatform.dao.CourseDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.model.Category;
import org.skillspark.onlinelearningplatform.model.Chapter;
import org.skillspark.onlinelearningplatform.model.Course;

/**
 *
 * @author lolip
 */
@WebServlet(name = "ChapterServlet", value = "/ChapterServlet")
public class ChapterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
           try{
                switch (route){
                    case "create" :
                       showCreateForm(request,response);
                       break;
                    case "store":
                        break;
                    case "edit":
                        break;
                    case "update":
                        break;
                    case "delete":
                        break;
                    default:
                        showListIndex(request,response);
                        break;
                }
           }catch(SQLException ex){
               
           }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void showListIndex(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/chapter/index.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        String course_name = request.getParameter("name");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        ChapterDao chapterDao = new ChapterDao(dbConnection);
            
        List<Chapter> listChapter = chapterDao.listAll(id);
        request.setAttribute("listChapter",listChapter);
        request.setAttribute("course_name",course_name);
        request.setAttribute("course_id",id);
         
        dispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException  {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/chapter/create.jsp");
        int course_id = Integer.parseInt(request.getParameter("id"));
        String course_name = request.getParameter("name");

        request.setAttribute("course_id",course_id);
        request.setAttribute("course_name",course_name);
        dispatcher.forward(request,response);
    }

}
