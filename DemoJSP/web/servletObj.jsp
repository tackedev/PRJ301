<%-- 
    Document   : servletObj
    Created on : Jun 4, 2021, 8:55:36 PM
    Author     : tackedev
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ServletObj</title>
    </head>
    <body>
        <h1>Hello to TimeZone</h1>
        <% Calendar calendar = Calendar.getInstance(); %>
        Today's Date and time is: <%= new Date()%><br/>
        The JSP Page is created by <%= ((HttpServlet) page).getServletInfo()%><br/>
    </body>
</html>
