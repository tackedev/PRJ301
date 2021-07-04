<%-- 
    Document   : myDate
    Created on : Jun 30, 2021, 10:15:07 AM
    Author     : tackedev
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My date</title>
    </head>
    <body>
        <%= new Date().toLocaleString() %>
    </body>
</html>
