/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.cart;

/**
 *
 * @author tackedev
 */
public class NotEnoughQuantityException extends Exception {

    /**
     * Creates a new instance of <code>NotEnoughQuantityException</code> without
     * detail message.
     */
    public NotEnoughQuantityException() {
    }

    /**
     * Constructs an instance of <code>NotEnoughQuantityException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NotEnoughQuantityException(String msg) {
        super(msg);
    }
}
