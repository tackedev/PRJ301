/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import tackedev.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class OrderDetailDAO implements Serializable {
    
    public boolean addOrderDetail(OrderDetailDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Insert Into OrderDetail (sku, price, quantity, total) "
                           + "Values (?, ?, ?, ?)";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSku());
                stm.setBigDecimal(2, dto.getPrice());
                stm.setInt(3, dto.getQuantity());
                stm.setBigDecimal(4, dto.getTotal());
                //4. Execute
                int row = stm.executeUpdate();
                //5. process
                if (row > 0) {
                    return true;
                }
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
    
    public boolean addOrderDetails(List<OrderDetailDTO> orderDetailList) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int row = 0;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                con.setAutoCommit(false);
                //2. Create SQL String
                String sql = "Insert Into OrderDetail (sku, price, quantity, total) "
                           + "Values (?, ?, ?, ?)";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //traverse productList
                for (OrderDetailDTO dto : orderDetailList) {
                    stm.setString(1, dto.getSku());
                    stm.setBigDecimal(2, dto.getPrice());
                    stm.setInt(3, dto.getQuantity());
                    stm.setBigDecimal(4, dto.getTotal());
                    //4. Execute statement
                    row += stm.executeUpdate();
                }
                con.commit();
                //5. Process
                if (row == orderDetailList.size()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                con.rollback();
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
