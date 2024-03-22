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
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.skillspark.onlinelearningplatform.dao.CategoryDao;
import org.skillspark.onlinelearningplatform.dao.DatabaseConnection;
import org.skillspark.onlinelearningplatform.dao.UsersDao;
import org.skillspark.onlinelearningplatform.model.Category;

/**
 *
 * @author lolip
 */

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String route = request.getParameter("route");
           try{
                switch (route){
                    case "create" :
                        showCreateForm(request,response);
                       break;
                    case "store":
                        storeCategory(request,response);
                        break;
                    case "edit":
                        showEditForm(request,response);
                        break;
                    case "update":
                        updateCategory(request,response);
                        break;
                    case "delete":
                        deleteCategory(request,response);
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
        doGet(request,response);
    }
    

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/categories/create.jsp");
        dispatcher.forward(request,response);
    }
    
    private void showListIndex(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/categories/index.jsp");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        CategoryDao catDao = new CategoryDao(dbConnection);
            
        List<Category> listCat = catDao.listAll();
        request.setAttribute("listCategory",listCat);
        
        dispatcher.forward(request,response);
    }

    private void storeCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        String cat_name = request.getParameter("cat_name");
        String cat_description = request.getParameter("cat_description");

        try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            HttpSession session = request.getSession();

            CategoryDao catDao = new CategoryDao(dbConnection);
            catDao.store(cat_name, cat_description);
            request.getSession().setAttribute("success", "Category succesffully added");
            response.sendRedirect("/CategoryServlet?route=index");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DatabaseConnection dbConnection = new DatabaseConnection();
        CategoryDao catDao = new CategoryDao(dbConnection);
        
        Optional<Category> cat = catDao.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/categories/edit.jsp");
        cat.ifPresent(s->request.setAttribute("category",s));
        dispatcher.forward(request,response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int cat_id = Integer.parseInt(request.getParameter("cat_id"));
        String cat_name = request.getParameter("cat_name");
        String cat_description = request.getParameter("cat_description");
        
        Category cat = new Category(cat_id,cat_name,cat_description);
        DatabaseConnection dbConnection = new DatabaseConnection();
        CategoryDao catDao = new CategoryDao(dbConnection);
        
        catDao.update(cat);
        request.getSession().setAttribute("success", "Category succesffully updated");
        response.sendRedirect("/CategoryServlet?route=index");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DatabaseConnection dbConnection = new DatabaseConnection();
        CategoryDao catDao = new CategoryDao(dbConnection);
            
        Category cat = new Category(id);
        
        catDao.delete(cat);
        request.getSession().setAttribute("success", "Category succesffully deleted");
        response.sendRedirect("/CategoryServlet?route=index");
    }
    
}