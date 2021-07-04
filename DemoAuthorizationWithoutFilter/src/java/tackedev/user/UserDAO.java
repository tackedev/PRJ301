/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.user;

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
public class UserDAO implements Serializable {
    
    public UserDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.getConnection();
            if (con != null) {
                String sql = "Select userId, fullName, role "
                           + "From tbl_User "
                           + "Where userId=? And password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userId = rs.getString(1);
                    String fullName = rs.getString(2);
                    int role = rs.getInt(3);
                    UserDTO dto = new UserDTO(userId, fullName, role);
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
