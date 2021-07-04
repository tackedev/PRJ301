<%-- 
    Document   : makeException
    Created on : Jun 4, 2021, 7:45:17 PM
    Author     : tackedev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scripting</title>
    </head>
    <body>
        <h1>Scripting Demo</h1>
        <%! 
            String s = "declaration";
            public void test() {
                System.out.println("This is a test function!!!");
            }
        %>
        <%
            String s = "Scriptlet";
            
            public void test1() {
                System.out.println("This is a function in scriptlet");
            }
        %>
    </body>
</html>
