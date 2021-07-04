/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.user;

import java.io.Serializable;

/**
 *
 * @author tackedev
 */
public class UserDAO implements Serializable {
    
    public boolean checkLogin(String username, int password) {
        return true;
    }
}
