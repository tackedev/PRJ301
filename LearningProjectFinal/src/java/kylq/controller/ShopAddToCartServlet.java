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
import kylq.cart.Cart;
import kylq.cart.NotEnoughQuantityException;
import org.apache.log4j.Logger;

/**
 *
 * @author tackedev
 */
public class ShopAddToCartServlet extends HttpServlet {
    
    private final Logger LOGGER = Logger.getLogger(ShopAddToCartServlet.class);
    
    private final String LOAD_SHOP_CONTROLLER = "shop";

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
        
        boolean foundErrors = false;
        
        try {
            //1. Goes to Cart's place
            HttpSession session = request.getSession();
            //2. Tackes Cart
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }
            //3. Takes item 
            String sku = request.getParameter("txtSku");
            
            //4. Drops item to Cart
            cart.addItemToCart(sku);
            session.setAttribute("CART", cart);
        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex);
            foundErrors = true;
        } catch (NotEnoughQuantityException ex) {
            // because if not enough quantity, button AddToCart will disable
            // this exception only catch when user request by custom url
            // So, only need redirect agian to Shop page without error notification
        } finally {
            if (!foundErrors) {
                response.sendRedirect(LOAD_SHOP_CONTROLLER);
            } else {
                response.sendError(500);
            }
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
