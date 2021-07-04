/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener.
 *
 * @author tackedev
 */
public class MyRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("destroyed in MyRequest is invoked!!!");
        sre.getServletRequest().removeAttribute("REQUEST");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("context in MyRequest is invoked!!!");
        sre.getServletRequest().setAttribute("REQUEST", "ADD");
    }
}
