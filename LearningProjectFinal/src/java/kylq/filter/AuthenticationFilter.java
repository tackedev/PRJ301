/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylq.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tackedev
 */
public class AuthenticationFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthenticationFilter() {
    }    
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
                
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        //get authenticationList from application scope
        ServletContext context = httpRequest.getServletContext();
        List<String> authenticationList = (List<String>) context.getAttribute("AUTHENTICATION_LIST");
        
        //check existed authenticated user
        HttpSession session = httpRequest.getSession(false);
        boolean existedAuthenticatedUser = session != null && session.getAttribute("USER") != null;
        
        String action = httpRequest.getServletPath().substring(1); //getServletPath() return /servletName, then we remove '/'
        if (action.equals("login")) {
            //check if existed authenticated user, not allow to access login again
            if (existedAuthenticatedUser) {
                httpResponse.sendRedirect("search");
            } else {
                chain.doFilter(request, response);
            }
        } else if (authenticationList.contains(action)) {
            //check if not existed authenticated user, not allow to access
            if (!existedAuthenticatedUser) {
                httpResponse.sendRedirect("login");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
        
//        //check existed session and existed authenticated user
//        
//        if (session == null || session.getAttribute("USER") == null) {
//            //forward to login page
//            RequestDispatcher rd = httpRequest.getRequestDispatcher(roadmap.get("login"));
//            rd.forward(request, response);
//        } else {
//            //when existed authenticated user, not permiss accessing login page
//            String resource = httpRequest.getServerName().substring(1);
//                        
//            if (resource.equals("login")) {
//                httpResponse.sendRedirect("search");
//            } else {
//                chain.doFilter(request, response);
//            }            
//        }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenticationFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenticationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
