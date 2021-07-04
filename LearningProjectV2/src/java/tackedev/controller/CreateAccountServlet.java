/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tackedev.registration.RegistrationDAO;
import tackedev.registration.RegistrationDTO;
import tackedev.registration.RegistrationInsertError;

/**
 *
 * @author tackedev
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.html";
    private final String ERROR_PAGE = "createNewAccount.jsp";

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
        
        String url = ERROR_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundErr = false;
        
        try {
            //1. Check all user errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundErr = true;
                errors.setUsernameLengthErr("Username is required from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required from 6 to 30 characters");
            } else if (!confirm.trim().equals(password)) {
                foundErr = true;
                errors.setConfirmNoMatch("Confirm must match password");
            }
            if (fullName.trim().length() < 2 || password.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthErr("Fullname is required from 2 to 50 characters");
            }
            
            if (foundErr) {
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                //2. Insert to DB - call DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createAccount(dto);
                
                if (result) {
                    url = LOGIN_PAGE;
                }//end account is created
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet _ Naming " + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted("Username is duplicated");
                request.setAttribute("INSERT_ERRORS", errors);
            }
        } finally {
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
