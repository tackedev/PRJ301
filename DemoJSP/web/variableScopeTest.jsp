<%-- 
    Document   : variableScopeTest
    Created on : Jun 4, 2021, 7:58:58 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Variable Test</title>
    </head>
    <body>
        <%!
            String s = "declaration";
            public void test() {
                System.out.println("This is a test function!!!");
            }
        %>
        <%
            String s = "scriptlet";
        %>
    </body>
</html>
