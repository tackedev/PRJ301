/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Web application lifecycle listener.
 *
 * @author tackedev
 */
public class MyRequestAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Add is activated-");
        String name = srae.getName();
        String oldValue = srae.getValue().toString();
        String newValue = srae.getServletRequest().getAttribute(name).toString();
        System.out.println("Name: " + name + " -old: " + oldValue + " -new: " + newValue);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("Remove is activated-");
        String name = srae.getName();
        String oldValue = srae.getValue().toString();
        System.out.println("Name: " + name + " -old: " + oldValue);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("Replace is activated-");
        String name = srae.getName();
        String oldValue = srae.getValue().toString();
        String newValue = srae.getServletRequest().getAttribute(name).toString();
        System.out.println("Name: " + name + " -old: " + oldValue + " -new: " + newValue);
    }
}
