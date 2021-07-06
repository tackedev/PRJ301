/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kylq.registration.RegistrationDAO;
import kylq.registration.RegistrationDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author tackedev
 */
public class UpdateEditServlet extends HttpServlet {
    
    private final Logger LOGGER = Logger.getLogger(UpdateEditServlet.class);

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
        
        String button = request.getParameter("btnAction");
        
        String url = "editAccountPage";
        
        try {
            if (button.equals("Confirm")) {
                //Call DAO to update 
                RegistrationDAO dao = new RegistrationDAO();
                //get EDIT_USER from session scope
                HttpSession session = request.getSession();
                RegistrationDTO dto = (RegistrationDTO) session.getAttribute("EDIT_USER");
                if (dao.updateEditRegistration(dto)) {
                    //get LAST_SEARCH_VALUE from session scope
                    String lastSearchValue = (String) session.getAttribute("LAST_SEARCH_VALUE");
                    
                    url = "searchAccountAction?txtSearch=" + lastSearchValue;
                    
                    //clear EDIT_USER and LAST_SEARCH_VALUE attribute in session scope
                    session.removeAttribute("EDIT_USER");
                    session.removeAttribute("LAST_SEARCH_VALUE");
                }//end update successfully
            }
        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex);
            response.sendError(500);
        } finally {
            response.sendRedirect(url);
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
