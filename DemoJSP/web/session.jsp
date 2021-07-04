<%-- 
    Document   : session
    Created on : Jun 4, 2021, 8:37:12 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (pageContext.getAttribute("pageCount") == null) {
        pageContext.setAttribute("pageCount", new Integer(0));
    }
    if (session.getAttribute("sessionCount") == null) {
        session.setAttribute("sessionCount", new Integer(0));
    }
    if (application.getAttribute("appCount") == null) {
        application.setAttribute("appCount", new Integer(0));
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Object</title>
    </head>
    <body>
        <h1>Session, Application, PageContext</h1>
        <%
            Integer count = (Integer) pageContext.getAttribute("pageCount");
            pageContext.setAttribute("pageCount", new Integer(count.intValue()) + 1);
            
            Integer count2 = (Integer) session.getAttribute("sessionCount");
            session.setAttribute("sessionCount", new Integer(count2.intValue() + 1));
            
            Integer count3 = (Integer) application.getAttribute("appCount");
            application.setAttribute("appCount", new Integer(count3.intValue() + 1));
        %>
        
        <b>Page count =</b>
        <%= pageContext.getAttribute("pageCount") %><br/>
        
        <b>Session Count =</b>
        <%= session.getAttribute("sessionCount") %><br/>
        
        <b>Application Count =</b>
        <%= application.getAttribute("appCount") %><br/>
        
        <b>Tiome =</b>
        <%= new java.sql.Time(System.currentTimeMillis()) %><br/>
                
    </body>
</html>
