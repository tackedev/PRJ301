/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kylq.registration.RegistrationDAO;
import kylq.registration.RegistrationInsertError;
import org.apache.log4j.Logger;

/**
 *
 * @author tackedev
 */
public class AccountUpdateServlet extends HttpServlet {
    
    private final Logger LOGGER = Logger.getLogger(AccountUpdateServlet.class);
    
    private final String SEARCH_ACCOUNT_CONTROLLER = "searchAccountAction";

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
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        Boolean role = request.getParameter("chkRole") != null;
                
        try {
            //validation input
            //password (6 - 30 chars)
            if (password.length() < 6 || 30 < password.length()) {
                RegistrationInsertError errors = new RegistrationInsertError();
                errors.setPasswordLengthErr("Password is required from 6 to 30 characters");
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                // Call DAO then updateRegistration
                RegistrationDAO dao = new RegistrationDAO();
                if (dao.updateRegistration(username, password, role)) {
                    //do nothing
                }
            }
        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex);
            response.sendError(500);
        } finally {
            //get roadmap from application scope
            Map<String, String> roadmap = (Map<String, String>) request.getServletContext().getAttribute("ROAD_MAP");
            String url = roadmap.get(SEARCH_ACCOUNT_CONTROLLER);
            //don't need to add txtSearch=lastSearchValue because we use forward, request param txtSearch is still existed
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
