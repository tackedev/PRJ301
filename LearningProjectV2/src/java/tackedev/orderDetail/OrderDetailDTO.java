/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.orderDetail;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author tackedev
 */
public class OrderDetailDTO implements Serializable {
    private String id;
    private String sku;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;

    public OrderDetailDTO() {
    }
    
    public OrderDetailDTO(String sku, BigDecimal price, int quantity, BigDecimal total) {
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public OrderDetailDTO(String id, String sku, BigDecimal price, int quantity, BigDecimal total) {
        this.id = id;
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
