<%-- 
    Document   : error
    Created on : Jun 4, 2021, 9:20:39 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error occurs</h1>
        <font color="red"><%= exception.toString() %><font/>
    </body>
</html>
