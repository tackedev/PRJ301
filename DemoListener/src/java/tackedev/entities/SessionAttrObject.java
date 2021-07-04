/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.entities;

import java.io.Serializable;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *
 * @author tackedev
 */
public class SessionAttrObject implements HttpSessionBindingListener {
    private String data;

    public SessionAttrObject() {
    }

    public SessionAttrObject(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.data;
    }
    
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("valueBound() " + getData());
    }
    
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("valueUnBound " + getData());
    }
    
}
