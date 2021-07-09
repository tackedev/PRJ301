/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import kylq.orderDetail.OrderDetailDAO;
import kylq.orderDetail.OrderDetailDTO;
import kylq.product.ProductDAO;
import kylq.product.ProductDTO;

/**
 *
 * @author tackedev
 */
public class Cart implements Serializable { 
    private Map<ProductDTO, Integer> items;

    public Cart() {
    }

    /**
     * @return the items
     */
    public Map<ProductDTO, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String sku) throws NamingException, SQLException {
        //check existed sku
        if (sku == null || sku.trim().isEmpty()) {
            return;
        }
        //check existed this.items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //call DAO for get real Product from sku
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductBySku(sku);
        //check dto existed in this.items
        int quantity = 1;
        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }
        //update this.items
        this.items.put(dto, quantity);
    }
    
    public int getItemQuantityBySku(String sku) {
        //check existed sku
        if (sku == null || sku.trim().isEmpty()) {
            return 0;
        }
        //check existed this.items
        if (this.items == null) {
            return 0;
        }
        // Create a temporary ProductDTO by sku
        ProductDTO dto = new ProductDTO(sku);
        //check dto (with sku) existed in this.items
        if (this.items.containsKey(dto)) {
            return this.items.get(dto);
        }
        return 0;
    }
    
    public void removeItem(String sku) {
        //check existed sku
        if (sku == null || sku.trim().isEmpty()) {
            return;
        }
        //check existed this.items
        if (this.items == null) {
            return;
        }
        // Create a temporary ProductDTO by sku
        ProductDTO dto = new ProductDTO(sku);
        this.items.remove(dto);
        //sure that there's no items which nothing in it
        if (this.items.isEmpty()) {
            this.items = null;
        }
    }
    
    public boolean checkout() throws NamingException, SQLException, Exception {
        //check existed this.items
        if (this.items == null) {
            return false;
        }
        //create OrderDetailDTO List
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        for (ProductDTO dto : items.keySet()) {
            //check enough quantity
            if (dto.getQuantity() < items.get(dto)) {
                return false;
                // Toi hoi anh Tien cach xu li
            }
            //create OrderDetailDTO
            String sku = dto.getSku();
            BigDecimal price = dto.getPrice();
            int quantity = items.get(dto);
            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));
            
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(sku, price, quantity, total);
            
            orderDetailList.add(orderDetailDTO);
        }
        //call DAO for checkout
        OrderDetailDAO dao = new OrderDetailDAO();
        return dao.insertOrderDetails(orderDetailList);
    }
}
