<%-- 
    Document   : scriptletExpression
    Created on : Jun 4, 2021, 7:35:43 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complex Expression Example</title>
    </head>
    <%
        String bgColor = request.getParameter("bgColor");
        boolean hasExplicitColor;
        if (bgColor != null) {
            hasExplicitColor = true;
        } else {
            hasExplicitColor = false;
            bgColor = "White";
        }
    %>
    <body bgColor="<%= bgColor%>">
    <center>
        <h1>Color testing</h1>
        <%
            if (hasExplicitColor) {
                out.println("Explicit color: " + bgColor);
            } else {
                out.println("Using default color");
            }
        %>
    </center>
        
    </body>
</html>
