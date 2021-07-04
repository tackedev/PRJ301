<%-- 
    Document   : directiveInclude
    Created on : Jun 30, 2021, 10:13:06 AM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo include</title>
    </head>
    <body>
        <h1>Directive Includes JSP</h1>
        Current date is 
        <%@include file="myDate.jsp" %>
    </body>
</html>
