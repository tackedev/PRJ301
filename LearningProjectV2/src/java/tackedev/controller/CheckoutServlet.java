/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tackedev.cart.Cart;
import tackedev.orderDetail.OrderDetailDAO;
import tackedev.orderDetail.OrderDetailDTO;
import tackedev.product.ProductDTO;

/**
 *
 * @author tackedev
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {
    
    private final String ERRORS_PAGE = "errors.html";
    
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
        
        String url = ERRORS_PAGE;
        
        try {
            //1. customer goes to cart's place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. customer takes cart
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    //3. customer takes items in cart
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        //call OrderDetailDAO
                        OrderDetailDAO dao = new OrderDetailDAO();
                        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
                        
                        for (ProductDTO product : items.keySet()) {
                            // create a OrderDetailDTO
                            BigDecimal price = product.getPrice();
                            int quantity = items.get(product);
                            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));
                            
                            OrderDetailDTO dto = new OrderDetailDTO(product.getSku(), price, quantity, total);
                            orderDetailList.add(dto);
                        }//end traverse items
                        
                        //write to DB
                        if (dao.addOrderDetails(orderDetailList)) {
                            url = "Dispatcher?btnAction=ViewOnlineShopping";
                        }
                    }//end existed items
                    
                    //remove current cart
                    session.removeAttribute("CART");
                }//end existed cart
            }//end existed session
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
