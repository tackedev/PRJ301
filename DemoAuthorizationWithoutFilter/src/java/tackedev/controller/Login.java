/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tackedev.user.UserDAO;
import tackedev.user.UserDTO;

/**
 *
 * @author tackedev
 */
public class Login {
    private final static String LOGIN_PAGE = "login.html";
    private final static String ADMIN_PAGE = "admin.jsp";
    private final static String USER_PAGE = "user.jsp";
    
    public static String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = LOGIN_PAGE;
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        
        UserDAO dao = new UserDAO();
        try {
            UserDTO dto = dao.checkLogin(username, password);
            if (dto !=  null) {
                HttpSession session = request.getSession();
                session.setAttribute("USER", dao);
                
                if (dto.getRole() == 0) {
                    url = USER_PAGE;
                } else {
                    url = ADMIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return url;
    }
}
