<%-- 
    Document   : firstJSP
    Created on : Jun 4, 2021, 11:05:22 AM
    Author     : tackedev
--%>

<%@page import="java.util.Date"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>First JSP Page</title>
    </head>
    <body>
        <!-- firstJSP.jsp -->
        <% out.println("Hello there!"); %><br>
        <%="Current date is" + new Date()%>
        <%--end Program --%>
    </body>
</html>
