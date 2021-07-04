<%-- 
    Document   : home
    Created on : Jul 1, 2021, 11:14:36 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="logoutAction">Logout</a>
        <h1 style="color: red">Welcome, ${sessionScope.USER.lastName}</h1>
    </body>
</html>