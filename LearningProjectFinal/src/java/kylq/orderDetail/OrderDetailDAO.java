/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import kylq.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class OrderDetailDAO implements Serializable {
    
    public boolean insertOrderDetails(List<OrderDetailDTO> orderDetailList) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int row = 0;
        
        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //turn off autoCommit
                con.setAutoCommit(false);
                //2. create sql string
                String sql = "Insert Into OrderDetail (sku, price, quantity, total) "
                           + "Values (?, ?, ?, ?)";
                //3. Create statement
                stm = con.prepareStatement(sql);
                for (OrderDetailDTO dto : orderDetailList) {
                    stm.setString(1, dto.getSku());
                    stm.setBigDecimal(2, dto.getPrice());
                    stm.setInt(3, dto.getQuantity());
                    stm.setBigDecimal(4, dto.getTotal());
                    //4. execute statement
                    row += stm.executeUpdate();
                }
                con.commit();
                //5. process
                if (row == orderDetailList.size()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                con.rollback();
                throw ex;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
}
