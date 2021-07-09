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
import javax.servlet.http.HttpSession;
import kylq.registration.DeleteRegistrationErrors;
import kylq.registration.RegistrationDAO;
import kylq.registration.RegistrationDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author tackedev
 */
public class DeleteAccountServlet extends HttpServlet {
    
    private final Logger LOGGER = Logger.getLogger(DeleteAccountServlet.class);
    
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
        
        try {
            //check deleteUser is not the Current User
            //Get USER in session scope, don't need to check existed session or existed USER in session
            //because have checked in Authentication Filter 
            HttpSession session = request.getSession();
            RegistrationDTO dto = (RegistrationDTO) session.getAttribute("USER");
            
            if (dto.getUsername().equals(username)) {
                DeleteRegistrationErrors deleteError = new DeleteRegistrationErrors();
                deleteError.setDeleteYourAccount("Cannot delete your account!");
                request.setAttribute("DELETE_ACCOUNT_ERRORS", deleteError);
                return; //run finally block
            }
            
                
            // Call DAO then call deleteRegistration
            RegistrationDAO dao = new RegistrationDAO();
            if (dao.deleteRegistration(username)) {
                //delete successfully, reload Search page
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
