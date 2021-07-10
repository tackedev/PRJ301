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
import kylq.registration.RegistrationDTO;
import kylq.registration.RegistrationInsertError;
import org.apache.log4j.Logger;

/**
 *
 * @author tackedev
 */
public class RegisterServlet extends HttpServlet {
    
    private final Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    
    private final String REGISTER_PAGE = "registerPage";
    private final String LOGIN_PAGE = "login";

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
        
        String url = REGISTER_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundError = false;
        
        try {
            //1. Check all user errors
            username = username.trim();
            if (username.length() < 6 || username.length() > 20) {
                foundError = true;
                errors.setUsernameLengErr("Username is required from 6 to 20 characters");
            }
            if (password.length() < 6 || password.length() > 30) {
                foundError = true;
                errors.setPasswordLengthErr("Password is required from 6 to 30 characters");
            } else if (!confirm.equals(password)) {
                foundError = true;
                errors.setConfirmNoMatch("Confirm must match password");
            }
            fullName = fullName.trim();
            if (fullName.length() < 2 || fullName.length() > 50) {
                foundError = true;
                errors.setFullNameLengthErr("Fullname is required from 2 to 50 characters");
            }
            
            if (foundError) {
                request.setAttribute("INSERT_ERRORS", errors);
                request.setAttribute("USERNAME", username);
                request.setAttribute("FULLNAME", fullName);
            } else {
                //2. Insert to DB - call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                
                if (dao.createRegistration(dto)) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (NamingException ex) {
            LOGGER.error(ex);
            response.sendError(500);
        } catch (SQLException ex) {
            LOGGER.error(ex);
            String exMsg = ex.getMessage();
            if (exMsg.contains("duplicate")) {
                errors.setUsernameIsExisted("Username is duplicated");
                request.setAttribute("INSERT_ERRORS", errors);
            }
        } finally {
            //get roadmap form Application Scope
            Map<String, String> roadmap = (Map<String, String>) request.getServletContext().getAttribute("ROAD_MAP");
            
            RequestDispatcher rd = request.getRequestDispatcher(roadmap.get(url));
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
