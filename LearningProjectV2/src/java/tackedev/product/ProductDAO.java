/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tackedev.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class ProductDAO implements Serializable {
    
    public List<ProductDTO> getProducts() throws NamingException, SQLException {
        List<ProductDTO> result = null;
        
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select sku, name, price, description, quantity "
                           + "From Product";
                //3. Create Statement
                stm = con.createStatement();
                //4 Execute statement
                rs = stm.executeQuery(sql);
                //5. Process
                while (rs.next()) {
                    // get fields
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    
                    ProductDTO dto = new ProductDTO(sku, name, price, description, quantity);
                    
                    //add to result list
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(dto);
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
        
        return result;
    } 
}
