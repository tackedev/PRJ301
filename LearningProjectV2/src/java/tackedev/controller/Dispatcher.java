/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tackedev
 */
public class Dispatcher extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.html";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    
    private final String REMEMBER_USER_CONTROLLER = "RememberUserServlet";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_NAME_CONTROLLER = "SearchNameServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    private final String ONLINE_SHOPPING_CONTROLLER = "OnlineShoppingServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String REMOVE_ITEMS_CONTROLLER = "RemoveItemsServlet";
    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";

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
        
        String url = LOGIN_PAGE;
        String button = request.getParameter("btnAction");
        
        try {
            if (button == null) {
                url = REMEMBER_USER_CONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_NAME_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_CONTROLLER;
            } else if (button.equals("ViewOnlineShopping")) {
                url = ONLINE_SHOPPING_CONTROLLER;
            } else if (button.equals("AddToCart")) {
                url = ADD_ITEM_TO_CART_CONTROLLER;
            } else if (button.equals("ViewCart")) {
                url = VIEW_CART_PAGE;
            } else if (button.equals("RemoveItems")) {
                url = REMOVE_ITEMS_CONTROLLER;
            } else if (button.equals("Checkout")) {
                url = CHECKOUT_CONTROLLER;
            } else if (button.equals("CreateAccount")) {
                url = CREATE_ACCOUNT_CONTROLLER;
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
