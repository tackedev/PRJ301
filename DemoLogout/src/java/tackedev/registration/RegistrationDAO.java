/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tackedev.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class RegistrationDAO implements Serializable {
    
    public RegistrationDTO checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                stm = con.prepareStatement("Select lastName, isAdmin as role "
                                         + "From Registration "
                                         + "Where username=? And password=?");
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String lastName = rs.getString(1);
                    boolean role = rs.getBoolean(2);
                    
                    RegistrationDTO dto = new RegistrationDTO(username, lastName, role);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
}
