<%-- 
    Document   : myDate.jsp
    Created on : Jun 4, 2021, 11:11:50 AM
    Author     : tackedev
--%>

<%@page import="java.util.Date"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Date JSP program</title>
    </head>
    <body>
        <%= new Date().toLocaleString() %>
    </body>
</html>
