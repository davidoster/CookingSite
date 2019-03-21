/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAOImpl.RecipeDAOImpl;
import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Recipe;

/**
 *
 * @author giorgoskoz
 */
public class SubscriberController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        
//        response.getParameter("userId");
        Database db = new Database();
        String server = "localhost:3306";
        String username = "root";
        String password = "root";
        String database = "cooking_site";
        String query = "SELECT * FROM `cooking_site`.`recipes`;";
        ResultSet rs = db.Database(server, database, username, password, query);

        response.setContentType("text/html;charset=UTF-8");       
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubscriberController at " + request.getContextPath() + "</h1>");
            
        // Business Logic Start
            
        try {
            while (rs.next()) {
                out.println("Recipe Id: " + rs.getString(1) + "&nbsp;" + 
                            "Title: " + rs.getString(2) + "&nbsp;" + 
                            "Description: " + rs.getString(3) + "&nbsp;" + "Image: " + rs.getString(4) + 
                            "&nbsp;&nbsp;<input type=\"checkbox\" name=\"addFavourite"+ rs.getString(1) +"\" value=\"favourite\"> Add to favourtites<br />");

            }
            if(!rs.first()){
                out.println("<h2>Wrong Credentials</h2>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        
//        response.getParameter("userId");
        Database db = new Database();
        String server = "localhost:3306";
        String username = "root";
        String password = "root";
        String database = "cooking_site";
        String query = "SELECT * FROM `cooking_site`.`recipes`;";
        ResultSet rs = db.Database(server, database, username, password, query);

        response.setContentType("text/html;charset=UTF-8");       
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubscriberController at " + request.getContextPath() + "</h1>");
            RecipeDAOImpl rDAOImpl = new RecipeDAOImpl();
            
            Recipe r = rDAOImpl.create("Recipe 1", "Super Recipe", "http://CookingSite/images/image1.jpg");
            request.setAttribute("recipe", r);
            
        // Business Logic Start
            
        try {
            while (rs.next()) {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/subscriber.jsp");
                rd.forward(request, response);
                /*out.println("Recipe Id: " + rs.getString(1) + "&nbsp;" + 
                            "Title: " + rs.getString(2) + "&nbsp;" + 
                            "Description: " + rs.getString(3) + "&nbsp;" + "Image: " + rs.getString(4) + 
                            "&nbsp;&nbsp;<input type=\"checkbox\" name=\"addFavourite"+ rs.getString(1) +"\" value=\"favourite\"> Add to favourtites<br />");
*/
            }
            if(!rs.first()){
                //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/noentry.jsp");
                //rd.forward(request, response);
                //out.println("<h2>Wrong Credentials</h2>");
                response.sendRedirect("/WEB-INF/views/noentry.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
}
