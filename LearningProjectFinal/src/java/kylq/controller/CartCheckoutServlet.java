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
import kylq.cart.Cart;
import kylq.cart.NotEnoughQuantityError;
import kylq.cart.NotEnoughQuantityException;
import org.apache.log4j.Logger;

/**
 *
 * @author tackedev
 */
public class CartCheckoutServlet extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(CartCheckoutServlet.class);
    
    private final String VIEW_CART_PAGE = "viewCart";
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
        
        String url = VIEW_CART_PAGE;

        //goes to cart's place
        HttpSession session = request.getSession(false);

        try {
            if (session == null) {
                return;
            }
            //takes cart
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                return;
            }
            
            //call checkout
            if (cart.checkout()) {
                // checkout successfully
                url = LOAD_SHOP_CONTROLLER;
                request.setAttribute("CHECKOUT_SUCCESSFULLY", "Checkout Successfully!!!");
                // remove CART
                session.removeAttribute("CART");
            }
        } catch (NamingException ex) {
            LOGGER.error(ex);
            foundErrors = true;
        } catch (SQLException ex) {
            LOGGER.error(ex);
            if (ex.getMessage().contains("The transaction ended in the trigger. The batch has been aborted")) {
                // It is also Not enough quantity error
                url = VIEW_CART_PAGE;
                NotEnoughQuantityError error = new NotEnoughQuantityError();
                error.setMsg("Not enough quantity. Please go to shop page and check newest product quantity status!");
                request.setAttribute("NOT_ENOUGH_QUANTITY_ERROR", error);
            } else {
                foundErrors = true;
            }
        } catch (NotEnoughQuantityException ex) {
            url = VIEW_CART_PAGE;
            NotEnoughQuantityError error = new NotEnoughQuantityError();
            error.setMsg("Not enough " + ex.getMessage() + " quantity. Please go to shop page and check newest product quantity status!");
            request.setAttribute("NOT_ENOUGH_QUANTITY_ERROR", error);
        } finally {
            if (!foundErrors) {
                //get roadmap from application scope
                Map<String, String> roadmap = (Map<String, String>) request.getServletContext().getAttribute("ROAD_MAP");
                RequestDispatcher rd = request.getRequestDispatcher(roadmap.get(url));
                rd.forward(request, response);
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
