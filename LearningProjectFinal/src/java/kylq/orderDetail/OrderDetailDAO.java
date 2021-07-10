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
    
    public boolean insertOrderDetails(int orderId, List<OrderDetailDTO> orderDetailList) throws NamingException, SQLException {
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
                String sql = "Insert Into OrderDetail (ordersId, sku, price, quantity, total) "
                           + "Values (?, ?, ?, ?, ?)";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                for (OrderDetailDTO dto : orderDetailList) {
                    stm.setString(2, dto.getSku());
                    stm.setBigDecimal(3, dto.getPrice());
                    stm.setInt(4, dto.getQuantity());
                    stm.setBigDecimal(5, dto.getTotal());
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
