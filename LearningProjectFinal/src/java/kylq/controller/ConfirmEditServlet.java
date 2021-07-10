/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kylq.registration.RegistrationDTO;
import kylq.registration.RegistrationInsertError;

/**
 *
 * @author tackedev
 */
public class ConfirmEditServlet extends HttpServlet {
    
    private final String EDIT_ACCOUNT_PAGE = "editAccountPage";
    private final String CONFIRM_EDIT_PAGE = "confirmEditPage";
    private final String SEARCH_CONTROLLER = "searchAccountAction";
    
    private void processUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = EDIT_ACCOUNT_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastName = request.getParameter("txtFullName");
        Boolean role = request.getParameter("chkRole") != null;
        
        //get edited dto
        lastName = lastName.trim();
        RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);
        //Replace old EDIT_USER in session scope
        HttpSession session = request.getSession();
        session.setAttribute("EDIT_USER", dto);
        
        //validate edit input
        //password (6 - 30 chars)
        //fulllname (2 - 50 chars)
        RegistrationInsertError errors = new RegistrationInsertError();
        boolean foundErrors = false;
        if (password.length() < 6 || 30 < password.length()) {
            foundErrors = true;
            errors.setPasswordLengthErr("Password is required from 6 to 30 characters");
        }
        if (lastName.length() < 2 || 50 < lastName.length()) {
            foundErrors = true;
            errors.setFullNameLengthErr("Fullname is required from 2 to 50 characters");
        }
        
        if (foundErrors) {
            //set errors to request scope
            request.setAttribute("INSERT_ERRORS", errors);
        } else {
            url = CONFIRM_EDIT_PAGE;
        }
        
        //get roadmap form application scope
        Map<String, String> roadmap = (Map<String, String>) request.getServletContext().getAttribute("ROAD_MAP");

        RequestDispatcher rd = request.getRequestDispatcher(roadmap.get(url));
        rd.forward(request, response);
    }
    
    private void processCancel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //get lastSearchValue from session scope
        HttpSession session = request.getSession();
        String lastSearchValue = (String) session.getAttribute("LAST_SEARCH_VALUE");
        
        //Remove EDIT_USER and LAST_SEARCH_VALUE in session
        session.removeAttribute("EDIT_USER");
        session.removeAttribute("LAST_SEARCH_VALUE");
        
        String url = SEARCH_CONTROLLER 
                   + "?txtSearch=" +  lastSearchValue;
        response.sendRedirect(url);
    }

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
        
        if (button.equals("Update")) {
            processUpdate(request, response);
        } else {
            processCancel(request, response);
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
