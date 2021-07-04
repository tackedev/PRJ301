/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.utils;

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
public class DBHelpers implements  Serializable {
    
    public Connection getConnection() throws NamingException, SQLException {
        //get filesystem
        Context context = new InitialContext();
        //get tomcat context
        Context tomcatContext = (Context) context.lookup("java:comp/env");
        //get DataSource
        DataSource ds = (DataSource) tomcatContext.lookup("Database");
        //return Conntection
        return ds.getConnection();
    }
    
}
