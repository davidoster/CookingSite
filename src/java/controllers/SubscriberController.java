/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
}
