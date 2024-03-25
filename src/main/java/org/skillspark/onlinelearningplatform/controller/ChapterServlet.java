/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.skillspark.onlinelearningplatform.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
@MultipartConfig
public class ChapterServlet extends HttpServlet {
    String globalPath =  "C:/Users/lolip/OneDrive/Documents/NetBeansProjects/OnlineLearningSystem/src/main/webapp/video/";
             
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String route = request.getParameter("route");
           try{
                switch (route){
                    case "create" :
                       showCreateForm(request,response);
                       break;
                    case "store":
                        storeChapter(request,response);
                        break;
                    case "edit":
                        showEditForm(request,response);
                        break;
                    case "update":
                        updateChapter(request,response);
                        break;
                    case "delete":
                        deleteChapter(request,response);
                        break;
                    default:
                        showListIndex(request,response);
                        break;
                }
           }catch(SQLException ex){
               System.err.println("Error sql: " + ex.getMessage());
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

    private void storeChapter(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String course_name = request.getParameter("course_name");
        String chapter_title = request.getParameter("chapter_title");
        String chapter_name = request.getParameter("chapter_name");
      
        int chapter_status = Integer.parseInt(request.getParameter("chapter_status"));
        String chapter_level = request.getParameter("chapter_level");
        String chapter_description = request.getParameter("chapter_description");
        
         try {
            DatabaseConnection dbConnection = new DatabaseConnection();
            HttpSession session = request.getSession();
            
            Part videopath = request.getPart("videopath");

            ChapterDao chapterDao = new ChapterDao(dbConnection);
            int chapter_id = chapterDao.store(course_id, chapter_title, chapter_name, null, null, chapter_description, chapter_status, chapter_level);
            
            String path = fileUpload(videopath,course_id,chapter_id);
            chapterDao.updateVideoPath(chapter_id,path);
            
            request.getSession().setAttribute("success", "Category succesffully added");
            response.sendRedirect("/ChapterServlet?route=index&id="+course_id+"&name="+course_name);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String course_name = request.getParameter("name");
        DatabaseConnection dbConnection = new DatabaseConnection();
        ChapterDao chapterDao = new ChapterDao(dbConnection);
        
        Optional<Chapter> chapter = chapterDao.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/tutor/content/chapter/edit.jsp");
        chapter.ifPresent(s->request.setAttribute("chapter",s));
        
        request.setAttribute("course_id",course_id);
        request.setAttribute("course_name",course_name);
        dispatcher.forward(request,response);
    }
    
    private void updateChapter(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("chapter_id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String course_name = request.getParameter("course_name");
        String chapter_title = request.getParameter("chapter_title");
        String chapter_name = request.getParameter("chapter_name");
      
        int chapter_status = Integer.parseInt(request.getParameter("chapter_status"));
        String chapter_level = request.getParameter("chapter_level");
        String chapter_description = request.getParameter("chapter_description");
        
        Chapter chapter = new Chapter(id,course_id,chapter_title,chapter_name,chapter_description,chapter_status,chapter_level);
        DatabaseConnection dbConnection = new DatabaseConnection();
        ChapterDao chapterDao = new ChapterDao(dbConnection);
        
        chapterDao.update(chapter);
        
        Part videopath = request.getPart("videopath");
        
        if(videopath.getSize()>0){
            String filePath = request.getParameter("tempt_video");
            deleteVideo(globalPath + filePath);
            String path = fileUpload(videopath,course_id,id);
            chapterDao.updateVideoPath(id,path);
        }
        
        request.getSession().setAttribute("success", "Category succesffully updated");
        response.sendRedirect("/ChapterServlet?route=index&id="+course_id+"&name="+course_name);
    }
    
    private void deleteChapter(HttpServletRequest request, HttpServletResponse response) throws SQLException ,ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));
        String course_name = request.getParameter("name");
        
        DatabaseConnection dbConnection = new DatabaseConnection();
        ChapterDao chapterDao = new ChapterDao(dbConnection);
            
        Chapter chapter = new Chapter(id);
        
        chapterDao.delete(chapter);
        request.getSession().setAttribute("success", "Chapter succesffully deleted");
         response.sendRedirect("/ChapterServlet?route=index&id="+course_id+"&name="+course_name);
    }
    
    private String fileUpload(Part videopath,int course_id,int chapter_id) throws SQLException ,ServletException, IOException {
        String path =  globalPath;
        String system_path = "/video/";
        String fileName = chapter_id+"_"+getFileName(videopath);

        OutputStream otpStream = null;  
        InputStream iptStream = null;  
        
        Files.createDirectories(Paths.get(path));
          
        try {  
            otpStream = new FileOutputStream(new File(path + File.separator + fileName));  
            iptStream = videopath.getInputStream();
  
            int read = 0;  
            final byte[] bytes = new byte[1024];  
              
            while ((read = iptStream.read(bytes)) != -1) {  
                otpStream.write(bytes, 0, read);  
            }  
        }  
        
        catch (FileNotFoundException fne){  
            System.err.println("Error file: " + fne.getMessage());
        }  
        finally {  
            if (otpStream != null) {  
                otpStream.close();  
            }  
            if (iptStream != null) {  
                iptStream.close();  
            }  
        }  
        return system_path + fileName;
    }
    
    private String getFileName(final Part part) {  
        final String partHeader = part.getHeader("content-disposition");  

        for (String content : part.getHeader("content-disposition").split(";")) {  
            if (content.trim().startsWith("filename")) {  
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");  
            }  
        }  
        return null;  
    }  
    
    private void deleteVideo(String filePath)
    {
        File file = new File(filePath);
        try{
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Video deleted successfully.");
                } else {
                    System.out.println("Failed to delete video.");
                }
            } else {
                System.out.println("Video does not exist.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting video: " + e.getMessage());
        }
    }

}
