<%-- 
    Document   : errorObj
    Created on : Jun 4, 2021, 9:13:39 PM
    Author     : tackedev
--%>
<%@ page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tag Example</title>
    </head>
    <body>
        <%! String name;%>
        <%
            name = request.getParameter("name");
            if (name == null) {
                name = "World";
            }
        %>
        <h1>Hello, <%=name%></h1>
        <% out.println("<H1>Hello" + name + "<H1/>"); %>
        <% int num = Integer.parseInt("a"); %>
    </body>
</html>
