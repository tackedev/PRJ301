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
import orders.OrdersDAO;

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

    public void addItemToCart(String sku) throws NamingException, SQLException, NotEnoughQuantityException {
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
        //check enough quantity
        if (dto.getQuantity() < quantity) {
            throw new NotEnoughQuantityException();
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
    
    private boolean checkEnoughAllProductsQuantity() throws NamingException, SQLException, NotEnoughQuantityException {
        //call DAO for get product to check
        ProductDAO productDAO = new ProductDAO();
        for (ProductDTO item : items.keySet()) {
            ProductDTO productDTO = productDAO.getProductBySku(item.getSku());
            
            if (productDTO.getQuantity() < items.get(item)) {
                throw new NotEnoughQuantityException(item.getName());
            }
        }
        return true;
    }

    private int createNewOrder() throws NamingException, SQLException {
        OrdersDAO dao = new OrdersDAO();
        int newOrderId = dao.insertNewOrder();
        return newOrderId;
    }

    private boolean insertOrderDetailList(int orderId) throws NamingException, SQLException {
        //create OrderDetailDTO List
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        
        for (ProductDTO item : items.keySet()) {
            //create OrderDetailDTO
            String sku = item.getSku();
            BigDecimal price = item.getPrice();
            int quantity = items.get(item);
            BigDecimal total = price.multiply(BigDecimal.valueOf(quantity));

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(sku, price, quantity, total);
            
            //add dto to list
            orderDetailList.add(orderDetailDTO);
        }
        //call DAO for checkout
        OrderDetailDAO dao = new OrderDetailDAO();
        return dao.insertOrderDetails(orderId, orderDetailList);
    }

    public boolean checkout() throws NamingException, SQLException, NotEnoughQuantityException {
        //check existed this.items
        if (this.items == null) {
            return false;
        }
        
        //check enough all products' quantity
        checkEnoughAllProductsQuantity();
        //if not enough, it will throw NotEnoughQuantityException
        
        //create new orders
        int newOrderId = createNewOrder();
        //if there are some errors, it will throw exception

        //insert orders detail
        return insertOrderDetailList(newOrderId);
        //if there are some errors, it will throw exception
    }
}
