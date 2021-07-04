<%-- 
    Document   : jstlRedirect
    Created on : Jul 1, 2021, 9:11:48 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jstlRedirect</title>
    </head>
    <body>
        <h1>Demo JSTL Redirect</h1>
        <p>Hello world!</p>
        <%
            System.out.println("Before redirect");
        %>
        <c:redirect url="jspIncludeTag.jsp"/>
        <%
            System.out.println("After redirect");
        %>
    </body>
</html>
