/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tackedev.cart.Cart;
import tackedev.product.ProductDTO;

/**
 *
 * @author tackedev
 */
@WebServlet(name = "RemoveItemsServlet", urlPatterns = {"/RemoveItemsServlet"})
public class RemoveItemsServlet extends HttpServlet {

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
        
        try {
            //1. custemer goes to cart's place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. customer takes cart
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    //3. customer takes items
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. get selected items
                        String[] removedItems = request.getParameterValues("chkItem");
                        if (removedItems != null) {
                            //5. Remove each item from cart
                            for (String item: removedItems) {
                                cart.removeItemFromCart(item);
                            }//end traverse each item
                            session.setAttribute("CART", cart);
                        }//end removedItems had choice
                    }//end existed items
                }//end existed cart
            }//end existed session
        } finally {
            //6. Refresh view cart again
            String urlRewriting = "Dispatcher"
                                + "?btnAction=ViewCart";
            response.sendRedirect(urlRewriting);
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
