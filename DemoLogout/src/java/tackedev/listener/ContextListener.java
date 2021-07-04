/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tackedev.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author tackedev
 */
public class ContextListener implements ServletContextListener {
    
    private final String ROADMAP_FILE = "WEB-INF/roadmap.txt";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        // load roadmap file
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/" + ROADMAP_FILE);

        FileReader fr = null;
        BufferedReader br = null;
        Map<String, String> roadMap = new HashMap<>();
        try {
            fr = new FileReader(new File(realPath));
            br = new BufferedReader(fr);
            
            String line = null;
            while ((line = br.readLine()) != null) {
                int seperatorIndex = line.indexOf('=');
                String key = line.substring(0, seperatorIndex);
                String value = line.substring(seperatorIndex + 1);
                roadMap.put(key, value);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        context.setAttribute("ROAD_MAP", roadMap);
        
        // initialize log4j
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        realPath = context.getRealPath("/") + log4jConfigFile;
        PropertyConfigurator.configure(realPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
