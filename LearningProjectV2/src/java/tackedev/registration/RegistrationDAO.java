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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tackedev.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class RegistrationDAO implements Serializable {
    
    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Select username "
                           + "From Registration "
                           + "Where username = ? And password = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null ) {
                con.close();
            }
        }
        return false;
    }
    
    public List<RegistrationDTO> searchName(String searchValue) throws NamingException, SQLException {
        List<RegistrationDTO> result = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Select username, password, lastName, isAdmin as role "
                           + "From Registration "
                           + "Where lastName Like ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //get fields
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastName");
                    boolean role = rs.getBoolean("role");
                    
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);
                    
                    // Create result List
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
    
    public boolean updateAccount(String username, String password, boolean role) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create Sql String
                String sql = "Update Registration "
                           + "Set password = ?, isAdmin = ? "
                           + "Where username = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. Execute
                int row = stm.executeUpdate();
                //5. Process
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
    
    public boolean deleteAccount(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create sql String
                String sql = "Delete From Registration "
                           + "Where username = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute
                int row = stm.executeUpdate();
                //5. Process
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
    
    public boolean createAccount(RegistrationDTO dto) throws NamingException, SQLException  {
        if (dto == null) {
            return false;
        }
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create Sql String
                String sql = "Insert Into Registration (username, password, lastName, isAdmin) "
                           + "Values(?, ?, ?, ?)";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastName());
                stm.setBoolean(4, dto.isRole());
                //4. Execute
                int row = stm.executeUpdate();
                //5. Process
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
}
