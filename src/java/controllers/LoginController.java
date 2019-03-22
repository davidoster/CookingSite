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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String server = "localhost:3306";
        String username ="root";
        String password = "root";
        String database = "cooking_site";
        String login_username = request.getParameter("username");
        String login_password = request.getParameter("password");
        String query = "SELECT * FROM `cooking_site`.`users` " + 
                    "WHERE `username` = '" + login_username + "' " +
                    "AND `password` = '"   + login_password + "';";
        Database db = new Database();
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
                
                // Business Logic Start
                try {
                    if(rs.first())
                    {
                        int user_id = rs.getInt(1);
                        Database db2 = new Database();
                        String query2 = "SELECT * FROM `cooking_site`.`user_roles` WHERE users_id = '" + user_id + "'";
                        ResultSet rs2 = db2.Database(server, database, username, password, query2);
                        rs2.first();
                        if(rs2.getString(3).equals("SUBSCRIBER")) {
                            rs2.close();
                            RequestDispatcher rd = request.getRequestDispatcher("subscriber");
                            //RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                            rd.forward(request, response);
                        }
                        // logged in, check for other roles, ADMINISTRATOR
                        else {
                            rs2.close();
                            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/test.jsp");
                            rd.forward(request, response);
                        }
                    }
                    // wrong credentials
                    else
                    {
                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                        String s = "Wrong credentials! Try again!";
                        //request.setAttribute("status", s);
                        request.setAttribute("status", new String("Wrong credentials! Try again!"));
                        
                        rd.forward(request, response);
                        //out.println("<h1>Wrong credentials</h1><br /><p>Redirecting...</p>");
                        
                        //response.sendRedirect("http://localhost:8084/CookingSite/noentry.jsp");
                        
                        //response.sendRedirect("index.jsp");
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Business Logic End
                
                out.println("</body>");
                out.println("</html>");
            }    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
