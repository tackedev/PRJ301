/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import tackedev.product.ProductDAO;
import tackedev.product.ProductDTO;

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
    
    public void addItemToCart(String id) throws NamingException, SQLException {
        //1. Check id is existed
        if (id == null) {
            return;
        }
        if (id.trim().isEmpty()){
            return;
        }
        //2. When id is existed, check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. When items is existed, chech existed id in items
        //get product which has sku == id
        ProductDAO productDAO = new ProductDAO();
        List<ProductDTO> productList = productDAO.getProducts();
        ProductDTO dto = productList.get(productList.indexOf(new ProductDTO(id)));
        
        int quantity = 1;
        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }
        //4. Update items
        this.items.put(dto, quantity);
    }
    
    public void removeItemFromCart(String id) {
        // check existed items
        if (this.items == null) {
            return;
        }
        // when existed item then check existed id in item
        ProductDTO dto = new ProductDTO(id);
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            // sure that there's no items witch nothing in it
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public int getItemQuantity(String id) {
        //check existed items
        if (this.items == null) {
            return 0;
        }
        // check existed id in items
        ProductDTO dto = new ProductDTO(id);
        if (!this.items.containsKey(dto)) {
            return 0;
        }
        return this.items.get(dto);
    }

}
