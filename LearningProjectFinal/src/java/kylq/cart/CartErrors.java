/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.cart;

import java.io.Serializable;

/**
 *
 * @author tackedev
 */
public class CartErrors implements Serializable {
    private String emptyCustomer;

    public CartErrors() {
    }

    /**
     * @return the emptyCustomer
     */
    public String getEmptyCustomer() {
        return emptyCustomer;
    }

    /**
     * @param emptyCustomer the emptyCustomer to set
     */
    public void setEmptyCustomer(String emptyCustomer) {
        this.emptyCustomer = emptyCustomer;
    }
}
