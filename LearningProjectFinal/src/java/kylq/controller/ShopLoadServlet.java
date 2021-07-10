/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kylq.product.ProductDAO;
import kylq.product.ProductDTO;
import org.apache.log4j.Logger;
/**
 *
 * @author tackedev
 */
public class ShopLoadServlet extends HttpServlet {
    
    private final Logger LOGGER = Logger.getLogger(ShopLoadServlet.class);
    
    private final String SHOP_PAGE = "shopPage";

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
            //call DAO to get Product List
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> productList = dao.getProductList();
            
            //set productList to requestScope
            request.setAttribute("PRODUCT_LIST", productList);
        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex);
            response.sendError(500);
        } finally {
            //get roadmap from application scope
            Map<String, String> roadmap = (Map<String, String>) request.getServletContext().getAttribute("ROAD_MAP");
            
            RequestDispatcher rd = request.getRequestDispatcher(roadmap.get(SHOP_PAGE));
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
