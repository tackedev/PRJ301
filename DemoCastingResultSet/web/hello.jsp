<%-- 
    Document   : hello
    Created on : Jun 26, 2021, 10:22:49 AM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            public String hello() {
                String msg = "Hello world";
                return msg;
            }
        %>
        Message from Scriptlet: <%hello();%><br/>
        Message from Expression <%=hello()%>
    </body>
</html>
