/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import kylq.utils.DBHelpers;

/**
 *
 * @author tackedev
 */
public class RegistrationDAO implements Serializable {
    
    public RegistrationDTO getRegistrationByUsernameAndPassword(String username, String password) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Select lastName, isAdmin as role "
                           + "From Registration "
                           + "Where username=? And password=?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute statment
                rs = stm.executeQuery();
                //5. Process 
                if (rs.next()) {
                    //get all fields
                    String lastName = rs.getString("lastName");
                    boolean role = rs.getBoolean("role");
                    
                    //create Registration DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);
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
    
    public boolean createRegistration(RegistrationDTO dto) throws NamingException, SQLException {
        //Check existed dto
        if (dto == null) {
            return false;
        }
        
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create sql string
                String sql = "Insert Into Registration (username, password, lastName, isAdmin) "
                           + "Values (?, ?, ?, ?)";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastName());
                stm.setBoolean(4, dto.isRole());
                //4. Execute statment
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
    
    public List<RegistrationDTO> getRegistrationsByLastName(String searchValue) throws NamingException, SQLException {
        List<RegistrationDTO> result = null;
        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. get connection
            con = DBHelpers.getConnection();
            if (con != null) {
                //2. Create sql String
                String sql = "Select username, password, lastName, isAdmin as role "
                           + "From Registration "
                           + "Where lastName Like ?";
                //3. Create statmement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Statement
                rs = stm.executeQuery();
                while (rs.next()) {
                    //get all fields
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastName");
                    Boolean role = rs.getBoolean("role");
                    
                    //create DTO to store result
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastName, role);
                    
                    //check exist result list
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
