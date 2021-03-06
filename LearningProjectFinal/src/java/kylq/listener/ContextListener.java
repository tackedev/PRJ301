/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author tackedev
 */
public class ContextListener implements ServletContextListener {
    
    private final Logger LOGGER = Logger.getLogger(ContextListener.class);
    
    private void configLog4j(ServletContext context) {
        //get real path to config file
        String path = context.getInitParameter("log4j-config-location");
        String realPath = context.getRealPath("/" + path);
        
        //initialized log4j
        PropertyConfigurator.configure(realPath);
    }
    
    private void loadRoadMapFromFile(ServletContext context) {
        //get real path to roadmap-location
        String path = context.getInitParameter("roadmap-location");
        String realPath = context.getRealPath("/" + path);
        
        //Initialize roadmap
        Map<String, String> roadmap = new HashMap<>();
        
        //Read file 
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(new File(realPath));
            br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                int seperatorIndex = line.indexOf('=');
                String action = line.substring(0, seperatorIndex);
                String resouce = line.substring(seperatorIndex + 1);
                roadmap.put(action, resouce);
            }
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex);
        } catch (IOException ex) {
            LOGGER.error(ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                LOGGER.error(ex);
            }
        }
        
        //set roadmap to Application Scope
        context.setAttribute("ROAD_MAP", roadmap);
    }
    
    private void loadAuthenticationList(ServletContext context) {
        //get real path to authenticationList-location
        String path = context.getInitParameter("authenticationList-location");
        String realPath = context.getRealPath("/" + path);
        
        //Initialize list
        List<String> authenticationList = new ArrayList<>();
        
        //Read file 
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(new File(realPath));
            br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                authenticationList.add(line);
            }
        } catch (FileNotFoundException ex) {
            LOGGER.error(ex);
        } catch (IOException ex) {
            LOGGER.error(ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                LOGGER.error(ex);
            }
        }
        
        //set this list to application scope
        context.setAttribute("AUTHENTICATION_LIST", authenticationList);
    }
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //get current context
        ServletContext context = sce.getServletContext();
        
        configLog4j(context);
        loadRoadMapFromFile(context);
        loadAuthenticationList(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
