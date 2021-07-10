/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import kylq.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class OrdersDAO implements Serializable {
    
    public int insertNewOrder(String customer) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Insert Into Orders (customer, total) "
                           + "Output Inserted.id "
                           + "Values (?, ?)";
                //3. create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, customer);
                stm.setInt(2, 0);
                //4. execute
                 rs = stm.executeQuery();
                //4. process
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return id;
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
        return -1;
    }
    
}
