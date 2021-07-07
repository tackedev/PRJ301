/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import kylq.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class ProductDAO {
    
    public List<ProductDTO> getProductList() throws NamingException, SQLException {
        List<ProductDTO> result = null;
        
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select sku, name, price, description, quantity "
                           + "From Product";
                //3. create statement
                stm = con.createStatement();
                //4. execute statement
                rs = stm.executeQuery(sql);
                //5. process
                while (rs.next()) {
                    //get all fiels
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    // create DTO
                    ProductDTO dto = new ProductDTO(sku, name, price, description, quantity);
                    //check existed result
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
    
    public ProductDTO getProductBySku(String sku) throws NamingException, SQLException {        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select name, price, description, quantity "
                           + "From Product "
                           + "Where sku=?";
                //3. create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, sku);
                //4. execute statement
                rs = stm.executeQuery();
                //5. process
                if (rs.next()) {
                    //get all fiels
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    // create DTO
                    ProductDTO dto = new ProductDTO(sku, name, price, description, quantity);
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
