/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tackedev
 */
public class DBHelpers implements Serializable {
    
    public static Connection getConnection() throws NamingException, SQLException {
        // Get filesystem
        Context context = new InitialContext();
        // Get tomcat context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        // Get DataSource
        DataSource ds = (DataSource) tomcatContext.lookup("Database");
        // get connection
        return ds.getConnection();
    }
}
