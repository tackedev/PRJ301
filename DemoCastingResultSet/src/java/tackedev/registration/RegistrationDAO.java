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
import tackedev.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class RegistrationDAO implements Serializable {
    
    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "Select username "
                           + "From Registration "
                           + "Where username=? And password=?";
                stm = con.prepareCall(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    try {
                        int a = rs.getInt(1);
                        System.out.println(a);
                    } catch (NumberFormatException ex) {
                        System.out.println("Liu liu");
                    }
                    
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
        return true;
    }
    
}
